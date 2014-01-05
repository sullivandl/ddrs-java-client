package com.digidata.services.rest.client.exceptions;

public class RelNotFoundException extends DdrsClientException {

	private static final long serialVersionUID = 1L;

	public RelNotFoundException(String rel) {
		super(String.format("The rel attribute '%s' was not found among the collection of links", rel));
	}
	
	public RelNotFoundException(String rel, Throwable inner) {
		super(String.format("The rel attribute '%s' was not found among the collection of links", rel), inner);
	}
}
