package com.digidata.services.rest.client;

public class DdrsOptions {

	private String userAgent;
	private int timeout;
	private String proxyHost;
	private int proxyPort;
	
	public DdrsOptions() {
		this.userAgent = "com.digidata.services.rest.client";
		this.timeout = 1000;
		this.proxyHost = null;
		this.proxyPort = 0;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
}
