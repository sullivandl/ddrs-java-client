package com.digidata.services.rest.client.spring;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.digidata.services.rest.client.entities.Error;
import com.digidata.services.rest.client.exceptions.DdrsException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CustomResponseErrorHandler implements ResponseErrorHandler {

	private ResponseErrorHandler defaultHandler = new DefaultResponseErrorHandler();
	
	@Override
	public void handleError(ClientHttpResponse clientResponse) throws IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Error error = mapper.readValue(clientResponse.getBody(), Error.class);
			throw DdrsException.New(error);
		} catch(JsonParseException ex) {
			defaultHandler.handleError(clientResponse);
		}
	}

	@Override
	public boolean hasError(ClientHttpResponse clientResponse) throws IOException {
		return defaultHandler.hasError(clientResponse);
	}

}
