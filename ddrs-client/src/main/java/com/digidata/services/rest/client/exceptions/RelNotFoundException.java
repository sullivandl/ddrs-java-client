package com.digidata.services.rest.client.exceptions;

/**
 * This exception is thrown when an entities link or collection cannot be
 * found by the given 'rel' value.
 * 
 * @author dan.sullivan
 *
 */
public class RelNotFoundException extends DdrsClientException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an exception with the rel value.
	 * @param rel the rel value not found.
	 */
	public RelNotFoundException(String rel) {
		super(String.format("The rel attribute '%s' was not found among the collection of links", rel));
	}
	
	/**
	 * Constructs an exception with the rel value.
	 * @param rel the rel value not found.
	 * @param inner the inner exception.
	 */
	public RelNotFoundException(String rel, Throwable inner) {
		super(String.format("The rel attribute '%s' was not found among the collection of links", rel), inner);
	}
}
