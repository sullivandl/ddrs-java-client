package com.digidata.services.rest.client.exceptions;

public class PropertyNotFoundException extends DdrsClientException {

	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(String name) {
		super(String.format("The property '%s' was not found.", name));
	}
	
	public PropertyNotFoundException(String name, Throwable inner) {
		super(String.format("The property '%s' was not found.", name), inner);
	}
}
