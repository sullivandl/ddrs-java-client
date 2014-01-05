package com.digidata.services.rest.client;

import org.apache.commons.lang.StringUtils;

import com.digidata.services.rest.client.entities.User;
import com.digidata.services.rest.client.exceptions.DdrsClientException;
import com.digidata.services.rest.client.spring.SpringRestFramework;

public class Ddrs {

	public static final String CONTENT_TYPE_JSON = "application/vnd.com.digidata.ddvs+json";
	private static final String USERS_ME = "users/me";
	
	private static IDdrsFactory ddrsFactory;
	private static IRestFramework framework = new SpringRestFramework();
	
	private IRestClient client;
	
	public static void Initialize(IDdrsFactory factory) {
		ddrsFactory = factory;
	}
	
	public static boolean isInitialized(){
		return ddrsFactory != null;
	}
	
	public static User StartFresh() {
		return StartFresh(false);
	}
	
	public static User StartFresh(boolean compact) {
		Link me = new Link();
		me.setHref(USERS_ME);
		return me.get(User.class, compact);
	}
	
	public static Ddrs getService() {
		if(!isInitialized())
			throw new DdrsClientException("Ddrs has not been initialized");
		
		return ddrsFactory.getDdrs();
	}
	
	static IRestClient getClient() {
		return getService().client;
	}
	
	public Ddrs(String baseUri, String username, String password) {
		this(baseUri, framework.getUsernamePassword(username, password), new DdrsOptions());
	}
	
	public Ddrs(String baseUri, String username, String password, DdrsOptions options) {
		this(baseUri, framework.getUsernamePassword(username, password), options);
	}
	
	public Ddrs(String baseUri, IClientCustomization customization, DdrsOptions options) {
		
		client = framework.getRestClient(baseUri);
		client.setContentType(CONTENT_TYPE_JSON);
		
		client.setTimeout(options.getTimeout());
		client.setUserAgent(options.getUserAgent());
		if(StringUtils.isNotEmpty(options.getProxyHost()) && options.getProxyPort() > 0)
			client.setProxy(options.getProxyHost(), options.getProxyPort());
		
		customization.customize(client);
	}
}
