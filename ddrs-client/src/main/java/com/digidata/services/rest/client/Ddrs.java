package com.digidata.services.rest.client;

import org.apache.commons.lang.StringUtils;

import com.digidata.services.rest.client.entities.User;
import com.digidata.services.rest.client.exceptions.DdrsClientException;
import com.digidata.services.rest.client.spring.SpringRestFramework;

/**
 * Provides the entry points to the DigiData REST Service client library.
 * Users must first call {@link #Initialize(IDdrsFactory)} to initialize
 * the library's session context storage and then call {@link #startFresh()} to
 * begin a session with the DDRS.
 * 
 * @author dan.sullivan
 *
 */
public class Ddrs {

	/**
	 * Baseline content type to make json requests.
	 */
	public static final String CONTENT_TYPE_JSON = "application/vnd.com.digidata.ddvs+json";
	
	private static final String USERS_ME = "users/me";
	
	private static IDdrsFactory ddrsFactory;
	private static IRestFramework framework = new SpringRestFramework();
	
	private IRestClient client;
	
	/**
	 * Initializes the DDRS library with the given factory.
	 * @param factory the factory to use.
	 */
	public static void Initialize(IDdrsFactory factory) {
		ddrsFactory = factory;
	}
	
	public static void Initialize(IDdrsFactory factory, IRestFramework restFramework) {
		ddrsFactory = factory;
		framework = restFramework;
	}
	
	/**
	 * Returns true if the {@link #Initialize(IDdrsFactory)} method has been called.
	 * @return true if library has been initialized; false otherwise. 
	 */
	public static boolean isInitialized(){
		return ddrsFactory != null;
	}
	
	/**
	 * Starts a new session with {@code compact=true}.
	 * 
	 * @see #startFresh(boolean)
	 */
	public static User startFresh() {
		Link me = new Link();
		me.setHref(USERS_ME);
		return me.get(User.class);
	}
	
	/**
	 * Starts a new session with the DDRS by request the User's 'home page' resource.
	 * 
	 * @param compact whether or not to return a compact representation of the {@link User} entity.
	 * @return the current user entity.
	 */
	public static User startFresh(boolean compact) {
		Link me = new Link();
		me.setHref(USERS_ME);
		return me.get(User.class, compact);
	}
	
	/**
	 * Gets the DDRS service from the configured factory.
	 * 
	 * @return the DDRS service
	 */
	public static Ddrs getService() {
		if(!isInitialized())
			throw new DdrsClientException("Ddrs has not been initialized");
		
		return ddrsFactory.getDdrs();
	}
	
	/**
	 * Gets the underlying REST client to interact with the server.
	 * 
	 * @return the REST client.
	 */
	static IRestClient getClient() {
		return getService().client;
	}
	
	/**
	 * Constructs a new DDRS session context with default {@link DdrsOptions}.
	 * 
	 * @param baseUri the base URI of the DDRS.
	 * @param username the DDRS login with which to authenticate.
	 * @param password the DDRS password with which to authenticate.
	 */
	public Ddrs(String baseUri, String username, String password) {
		this(baseUri, framework.getUsernamePassword(username, password), new DdrsOptions());
	}

	/**
	 * Constructs a new DDRS session context.
	 * 
	 * @param baseUri the base URI of the DDRS.
	 * @param username the DDRS login with which to authenticate.
	 * @param password the DDRS password with which to authenticate.
	 * @param options the custom options to use.
	 */
	public Ddrs(String baseUri, String username, String password, DdrsOptions options) {
		this(baseUri, framework.getUsernamePassword(username, password), options);
	}
	
	/**
	 * Constructs a new DDRS session context with additional customizations.
	 * 
	 * @param baseUri the base URI of the DDRS.
	 * @param customization the client request customizations.
	 * @param options the custom options to use.
	 */
	public Ddrs(String baseUri, IClientCustomization customization, DdrsOptions options) {
		
		client = framework.getRestClient(baseUri);
		client.setContentType(CONTENT_TYPE_JSON);
		
		client.setTimeout(options.getTimeout());
		client.setUserAgent(options.getUserAgent());
		client.setCompactDefault(options.isCompactDefault());
		if(StringUtils.isNotEmpty(options.getProxyHost()) && options.getProxyPort() > 0)
			client.setProxy(options.getProxyHost(), options.getProxyPort());
		
		customization.customize(client);
	}
}
