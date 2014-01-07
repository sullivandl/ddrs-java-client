package com.digidata.services.rest.client.spring;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.client.RestTemplate;

import com.digidata.services.rest.client.IRestClient;
import com.digidata.services.rest.client.UsernamePasswordCustomization;
import com.digidata.services.rest.client.exceptions.DdrsClientException;

/**
 * Applies the username and password for the {@link SpringRestClient}.
 * 
 * @author dan.sullivan
 *
 */
public class SpringUsernamePasswordCustomization extends
			UsernamePasswordCustomization {

	/**
	 * @see UsernamePasswordCustomization#UsernamePasswordCustomization(String, String)
	 */
	public SpringUsernamePasswordCustomization(String username, String password) {
		super(username, password);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void customize(IRestClient client) {
		if(!(client instanceof RestTemplate))
			throw new DdrsClientException("The client " + client + " is not a type of " + SpringRestClient.class);
		
		SpringRestClient template = (SpringRestClient) client;
		HttpClientBuilder builder = template.getHttpClientBuilder();
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(null, -1),
                new UsernamePasswordCredentials(getUsername(), getPassword()));
        
        builder.setDefaultCredentialsProvider(credsProvider);
	}
}
