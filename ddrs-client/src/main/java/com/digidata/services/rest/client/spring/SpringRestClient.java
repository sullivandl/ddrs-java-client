package com.digidata.services.rest.client.spring;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.digidata.services.rest.client.Document;
import com.digidata.services.rest.client.Entity;
import com.digidata.services.rest.client.IClientCustomization;
import com.digidata.services.rest.client.IRestClient;
import com.digidata.services.rest.client.exceptions.DdrsClientException;

/**
 * This is an implementation of the client library's {@link IRestClient} using Spring's
 * RestTemplate and the Jackson JSON Processor (Spring's default JSON processor).
 * 
 * @author dan.sullivan
 *
 */
public class SpringRestClient extends RestTemplate implements 
		IRestClient, ClientHttpRequestFactory {

	private HttpComponentsClientHttpRequestFactory requestFactory;
	private HttpClientBuilder httpClientBuilder;
	
	private MediaType mediaType;
	private int timeout;
	private String userAgent;
	private String baseUri;
	private boolean compactDefault;
	
	/**
	 * Constructs a new client with the DDRS base URI.
	 * @param baseUri
	 */
	public SpringRestClient(String baseUri) {
		this.baseUri = baseUri;
		this.httpClientBuilder = HttpClients.custom();
		this.setRequestFactory(this);
		this.setErrorHandler(new CustomResponseErrorHandler());
	}

	/**
	 * {@inheritDoc}
	 */
	public void setContentType(String contentType) {
		String[] splits = contentType.split("/");
		if(splits.length != 2)
			throw new DdrsClientException(String.format("ContentType '%s' is not valid", contentType));
		this.mediaType = new MediaType(splits[0], splits[1]);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCompactDefault(boolean compactDefault) {
		this.compactDefault = compactDefault;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setProxy(String proxyHost, int proxyPort) {
		if(StringUtils.isEmpty(proxyHost))
			throw new IllegalArgumentException("The proxyHost parameter cannot be null or empty");
		if(proxyPort <= 0)
			throw new IllegalArgumentException("The proxyPort parameter must be greater than zero");
		
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        getHttpClientBuilder().setRoutePlanner(routePlanner);
	}

	private <T> HttpEntity<T> buildRequest(T entity, Class<T> responseType) {
		return buildRequest(entity, responseType, this.compactDefault);
	}
	
	private <T> HttpEntity<T> buildRequest(T entity, Class<T> responseType, boolean compact) {
		HttpHeaders headers = new HttpHeaders();
		
		String qualifiedContentType = String.format("%1$s;v=1;c=%2$s", mediaType, (compact? "1" : "0"));
    	headers.set("Accept", qualifiedContentType);
    	headers.setContentType(mediaType);
    	headers.set("User-Agent", userAgent);
    	HttpEntity<T> request = new HttpEntity<T>(entity, headers);
    	
    	return request;
	}
	
	private URI buildUrl(String resource) {
		try {
			return new URI(baseUri + "/" + resource);
		} catch (URISyntaxException e) {
			throw new DdrsClientException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <T extends Document> T get(String resource, Class<T> responseType) {
		return get(resource, responseType, this.compactDefault);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <T extends Document> T get(String resource, Class<T> responseType,
			boolean compact) {
		URI url = buildUrl(resource);
    	return this.exchange(url, HttpMethod.GET, buildRequest("", String.class, compact), responseType).getBody();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public OutputStream get(String resource) {
		// TODO implement download
		throw new NotImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	public <T extends Entity> void put(String resource, T body,
			Class<T> bodyType) {
		URI url = buildUrl(resource);
		this.exchange(url, HttpMethod.PUT, buildRequest(body, bodyType), bodyType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void put(String resource, final InputStream stream) {
		URI url = buildUrl(resource);
		
		final RequestCallback callback = new RequestCallback() {
			public void doWithRequest(ClientHttpRequest request) throws IOException {
				IOUtils.copy(stream, request.getBody());
			}
		};
		
		// TODO: we are not properly setting headers (User-Agent, Content-Type, etc.)
		HttpMessageConverterExtractor<String> responseExtractor = new HttpMessageConverterExtractor<String>(String.class, this.getMessageConverters());
		this.execute(url, HttpMethod.PUT, callback, responseExtractor);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public <T extends Entity> T post(String resource, Class<T> responseType) {
		return post(resource, responseType, this.compactDefault);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <T extends Entity> T post(String resource, Class<T> responseType, boolean compact) {
		URI url = buildUrl(resource);
		
		return this.exchange(url, HttpMethod.POST, buildRequest("", String.class, compact), responseType).getBody();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <T extends Entity> T post(String resource, T body, Class<T> responseType) {
		return post(resource, body, responseType, this.compactDefault);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <T extends Entity> T post(String resource, T body, Class<T> responseType, boolean compact) {
		URI url = buildUrl(resource);
		
		return this.exchange(url, HttpMethod.POST, buildRequest(body, responseType, compact), responseType).getBody();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(String resource) {
		URI url = buildUrl(resource);
		this.exchange(url, HttpMethod.DELETE, buildRequest("", String.class), String.class);
	}
	
	/**
	 * Gets the http client builder. {@link IClientCustomization} instances may use this
	 * to modify the underlying HttpClient.
	 * 
	 * @return the client builder.
	 */
	protected HttpClientBuilder getHttpClientBuilder() {
		// A little protection
		if(requestFactory != null)
			throw new DdrsClientException("Client has been used and can no longer be customized.");
		
		return this.httpClientBuilder;
	}

	/**
	 * {@inheritDoc}
	 */
	public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
		if(requestFactory == null) {
			HttpClient client = httpClientBuilder.build();
			requestFactory = new HttpComponentsClientHttpRequestFactory(client);
			
			requestFactory.setReadTimeout(this.timeout * 1000);
			requestFactory.setConnectTimeout(this.timeout * 1000);
		}
		return requestFactory.createRequest(uri, httpMethod);
	}
}
