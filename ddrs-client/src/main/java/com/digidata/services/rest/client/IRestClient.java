package com.digidata.services.rest.client;

import java.io.InputStream;
import java.io.OutputStream;

import com.digidata.services.rest.client.spring.SpringRestClient;

/**
 * Generic interface for the DDRS client to interact with the server. Today, we have a single
 * implementation of this - {@link SpringRestClient} which uses Spring's RestTemplate.  Future
 * efforts may desire to use an alternative underlying framework and new IRestClient.
 * 
 * @see SpringRestClient
 * @author dan.sullivan
 *
 */
public interface IRestClient {

	/**
	 * Sets the MIME type to use in the Content-Type and Accept in HTTP requests.
	 * @param contentType the mime type.
	 */
	void setContentType(String contentType);
	
	/**
	 * Sets the User-Agent in HTTP requests.
	 * @param userAgent the User-Agent.
	 */
	void setUserAgent(String userAgent);
	
	/**
	 * Sets the timeout, in seconds, of all HTTP requests.
	 * @param timeout the timeout in seconds.
	 */
	void setTimeout(int timeout);
	
	/**
	 * Sets the proxy host and port.
	 * @param proxyHost the proxy host.
	 * @param proxyPort the proxy port.
	 */
	void setProxy(String proxyHost, int proxyPort);
	
	/**
	 * Sets whether or not methods request compact representations
	 * from the server or not.
	 * @param compact
	 */
	void setCompactDefault(boolean compact);
	
	/**
	 * Retrieve the resource with an HTTP GET.
	 * @param resource the resource to GET.
	 * @return the response's raw stream.
	 */
	OutputStream get(String resource);
	
	/**
	 * Retrieve the resource with an HTTP GET.
	 * @param resource the resource to GET.
	 * @param responseType the type to which the response should be converted.
	 * @return the converted object.
	 */
	<T extends Document> T get(String resource, Class<T> responseType);
	
	/**
	 * Retrieve the resource with an HTTP GET.
	 * @param resource the resource to GET.
	 * @param responseType the type to which the response should be converted.
	 * @param compact whether or not to a GET compact representation of the resource.
	 * @return the converted object.
	 */
	<T extends Document> T get(String resource, Class<T> responseType, boolean compact);
	
	/**
	 * Create or update the resource with an HTTP PUT.
	 * @param resource the resource location to PUT to.
	 * @param stream a raw data stream.
	 */
	void put(String resource, InputStream stream);
	
	/**
	 * Create or update the resource with an HTTP PUT.
	 * @param resource the resource location to PUT to.
	 * @param body the entity to include in the body.
	 * @param bodyType the entity type to serialize from.
	 */
	<T extends Entity> void put(String resource, T body, Class<T> bodyType);

	/**
	 * Create a resource with an HTTP POST.
	 * @param resource the resource location to POST to.
	 * @param body the entity to include in the body.
	 * @param responseType the type to serialize to and from.
	 * @return the resulting server side entity.
	 */
	<T extends Entity> T post(String resource, T body, Class<T> responseType);
	
	/**
	 * Create a resource with an HTTP POST.
	 * @param resource the resource location to POST to.
	 * @param body the entity to include in the body.
	 * @param responseType the type to serialize to and from.
	 * @param compact whether or not to return a compact representation from the server.
	 * @return  the newly created entity.
	 */
	<T extends Entity> T post(String resource, T body, Class<T> responseType, boolean compact);
	
	/**
	 * Create a resource with an HTTP POST without an entity body.
	 * @param resource the resource location to POST to.
	 * @param responseType the type to serialize from.
	 * @param compact whether or not to return a compact representation from the server.
	 * @return the newly created entity.
	 */
	<T extends Entity> T post(String resource, Class<T> responseType, boolean compact);
	
	/**
	 * Create a resource with an HTTP POST without an entity body.
	 * @param resource the resource location to POST to.
	 * @param responseType the type to serialize from.
	 * @return the newly created entity.
	 */
	<T extends Entity> T post(String resource, Class<T> responseType);
	
	/**
	 * Deletes the resource with an HTTP DELETE.
	 * @param resource the resource to delete.
	 */
	void delete(String resource);
}
