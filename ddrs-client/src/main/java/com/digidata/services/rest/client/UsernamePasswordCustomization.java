package com.digidata.services.rest.client;

/**
 * Abstract base class to customize an {@link IRestClient} with user name/password
 * credentials.
 * 
 * @author dan.sullivan
 *
 */
public abstract class UsernamePasswordCustomization implements IClientCustomization {

	private String username;
	private String password;
	
	/**
	 * Construct a new customization with the user name and password.
	 * @param username the user name with which to authenticate.
	 * @param password the password with which to authetnicate.
	 */
	public UsernamePasswordCustomization(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Gets the username.
	 * @return the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * @param username the username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * @param password the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract void customize(IRestClient client);
}
