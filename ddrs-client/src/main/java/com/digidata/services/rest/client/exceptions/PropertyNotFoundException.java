package com.digidata.services.rest.client.exceptions;

/**
 * This exception is thrown when an entity's property cannot be found.
 * 
 * @author dan.sullivan
 *
 */
public class PropertyNotFoundException extends DdrsClientException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an exception for the property of the given name.
	 * @param name the name of the property not found.
	 */
	public PropertyNotFoundException(String name) {
		super(String.format("The property '%s' was not found.", name));
	}
	
	/**
	 * Constructs an exception for the property of the given name.
	 * @param name the name of the property not found.
	 * @param inner the inner exception.
	 */
	public PropertyNotFoundException(String name, Throwable inner) {
		super(String.format("The property '%s' was not found.", name), inner);
	}
}
