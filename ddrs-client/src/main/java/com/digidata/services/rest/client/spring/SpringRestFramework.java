package com.digidata.services.rest.client.spring;

import com.digidata.services.rest.client.IRestFramework;
import com.digidata.services.rest.client.IRestClient;
import com.digidata.services.rest.client.UsernamePasswordCustomization;

/**
 * This is the implementation of the client's framework abstraction. It returns
 * instances of the {@link SpringRestClient}.
 * 
 * @author dan.sullivan
 *
 */
public class SpringRestFramework implements
		IRestFramework {

	/**
	 * {@inheritDoc}
	 */
	public IRestClient getRestClient(String baseUri) {
		return new SpringRestClient(baseUri);
	}

	/**
	 * {@inheritDoc}
	 */
	public UsernamePasswordCustomization getUsernamePassword(String username,
			String password) {
		return new SpringUsernamePasswordCustomization(username, password);
	}

}
