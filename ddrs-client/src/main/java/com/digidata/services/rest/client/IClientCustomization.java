package com.digidata.services.rest.client;

/**
 * Interface for users to customize the underlying {@link IRestClient}.
 * 
 * @see Ddrs#Ddrs(String, IClientCustomization, DdrsOptions)
 * @author dan.sullivan
 *
 */
public interface IClientCustomization {

	/**
	 * Applies customization to the client.
	 * @param client the client to customize.
	 */
	void customize(IRestClient client);
}
