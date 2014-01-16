package com.digidata.services.rest.client.examples;

import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.DdrsOptions;
import com.digidata.services.rest.client.IDdrsFactory;

/**
 * This is a simple implementation of the {@link IDdrsFactory}.  It manages the 
 * user credentials is instance variables.
 * 
 * @author dan.sullivan
 *
 */
public class SimpleDdrsFactory implements IDdrsFactory {

	private String baseUri;
	private String username;
	private String origUsername;
	private String password;
	private String origPassword;
	private DdrsOptions options;

	/**
	 * Create a new factory.
	 * @param baseUri the baseUri of the DDRS.
	 * @param username the username to login with.
	 * @param password the password to login with.
	 * @param options the options to apply to the Ddrs instance.
	 */
	public SimpleDdrsFactory(String baseUri, String username, String password, DdrsOptions options) {
		this.baseUri = baseUri;
		this.username = this.origUsername = username;
		this.password = this.origPassword = password;
		this.options = options;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Ddrs getDdrs() {
		return new Ddrs(baseUri, username, password, options);
	}

	/**
	 * Switches this instances user to a username and password.
	 * @param username the username to switch to.
	 * @param password the password to switch to.
	 */
	public void switchUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Reverts this instance to the user with which the factory was constructed.
	 */
	public void revertUser() {
		this.username = origUsername;
		this.password = origPassword;
	}
}
