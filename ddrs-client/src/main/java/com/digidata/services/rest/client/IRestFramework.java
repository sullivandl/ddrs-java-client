package com.digidata.services.rest.client;

public interface IRestFramework {

	IRestClient getRestClient(String baseUri);
	UsernamePasswordCustomization getUsernamePassword(String username, String password);
}
