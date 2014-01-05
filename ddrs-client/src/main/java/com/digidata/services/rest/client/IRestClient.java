package com.digidata.services.rest.client;

import java.io.InputStream;

public interface IRestClient {

	void setContentType(String contentType);
	void setUserAgent(String userAgent);
	void setTimeout(int timeout);
	void setProxy(String proxyHost, int proxyPort);
	
	<T extends Document> T get(String resource, Class<T> responseType);
	<T extends Document> T get(String resource, Class<T> responseType, boolean compact);
	
	<T extends Entity> T put(String resource, T body, Class<T> responseType);
	<T extends Entity> T put(String resource, T body, Class<T> responseType, boolean compact);
	void put(String resource, InputStream stream);
	
	<T extends Entity> T post(String resource, T body, Class<T> responseType);
	<T extends Entity> T post(String resource, T body, Class<T> responseType, boolean compact);
	<T extends Entity> T post(String resource, Class<T> responseType, boolean compact);
	<T extends Entity> T post(String resource, Class<T> responseType);
	
	void delete(String resource);
}
