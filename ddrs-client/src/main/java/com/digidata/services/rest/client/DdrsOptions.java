package com.digidata.services.rest.client;

/***
 * Contains the available options to configure the {@link Ddrs}.
 * 
 * @see Ddrs#Ddrs(String, String, String, DdrsOptions)
 * @author dan.sullivan
 *
 */
public class DdrsOptions {

	private String userAgent;
	private int timeout;
	private String proxyHost;
	private int proxyPort;
	private boolean compactDefault;
	
	/**
	 * Constructs the default options.
	 */
	public DdrsOptions() {
		this.userAgent = DdrsOptions.class.getPackage().getName();
		this.timeout = 1000;
		this.proxyHost = null;
		this.proxyPort = 0;
		this.compactDefault = true;
	}

	/**
	 * Gets the User-Agent to include in all HTTP requests to the server. Default is the package
	 * name of this class.
	 * @return the current User-Agent configuration.
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets the User-Agent to include in all HTTP requests to the server.
	 * @param userAgent a new User-Agent configuration.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Gets the timeout value, in seconds, for all HTTP requests to the server.  Default is 
	 * 1,000 seconds.
	 * @return the current timeout configuration.
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Sets the timeout value, in seconds, for all HTTP requests to the server.
	 * @param timeout a new timeout configuration.
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Gets the host name of a proxy server for all HTTP requests to the server.  Default is {@code null},
	 * indicating no proxy should be used.
	 * @return the current proxy host configuration.
	 */
	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * Sets the host name of a proxy server for all HTTP requests to the server.
	 * @param proxyHost a new proxy host configuration.
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	/**
	 * Gets the port of a proxy server for all HTTP requests to the server.  Default value
	 * is {@code 0}, indicating no proxy should be used.
	 * @return the current proxy port configuration.
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Sets the port of a proxy server for all HTTP requests to the server.
	 * @param proxyPort a new proxy port configuration.
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * Gets whether or not the REST client, by default, should request compact
	 * representations of documents. Default value is {@code true}
	 * 
	 * @return the compact default value.
	 */
	public boolean isCompactDefault() {
		return compactDefault;
	}
	
	/**
	 * Sets whether or not the REST client, by default, should request compact
	 * representations of documents.
	 * 
	 * @param compactDefault the compact default value.
	 */
	public void setCompactDefault(boolean compactDefault) {
		this.compactDefault = compactDefault;
	}
}
