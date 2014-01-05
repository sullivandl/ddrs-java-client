package com.digidata.services.rest.client.spring;

import com.digidata.services.rest.client.IRestFramework;
import com.digidata.services.rest.client.IRestClient;
import com.digidata.services.rest.client.UsernamePasswordCustomization;

public class SpringRestFramework implements
		IRestFramework {

	public IRestClient getRestClient(String baseUri) {
		return new SpringRestClient(baseUri);
	}

	public UsernamePasswordCustomization getUsernamePassword(String username,
			String password) {
		return new SpringUsernamePasswordCustomization(username, password);
	}

}
