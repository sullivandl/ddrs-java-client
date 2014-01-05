package com.digidata.services.rest.client;

public abstract class UsernamePasswordCustomization implements IClientCustomization {

	private String username;
	private String password;
	
	public UsernamePasswordCustomization(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public abstract void customize(IRestClient client);
}
