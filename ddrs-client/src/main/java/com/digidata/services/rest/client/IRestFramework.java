package com.digidata.services.rest.client;

/**
 * Interface to retrieve the necessary framework components.  Components include the implemented
 * {@link IRestClient} and internal customizations such as {@link UsernamePasswordCustomization}.
 * 
 * @author dan.sullivan
 *
 */
public interface IRestFramework {

	/**
	 * Gets the rest client to use for all server calls.
	 * @param baseUri the base DDRS URI.
	 * @return the rest client.
	 */
	IRestClient getRestClient(String baseUri);
	
	/**
	 * Gets a {@link IClientCustomization} to inject username/password credentials.
	 * @param username the user name with which to authenticate.
	 * @param password the password with which to authenticate.
	 * @return the customization.
	 */
	UsernamePasswordCustomization getUsernamePassword(String username, String password);
}
