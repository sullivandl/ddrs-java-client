package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

/**
 * This exception is thrown when an operation attempts to create an
 * entity which already exists.
 * 
 * @author dan.sullivan
 *
 */
public class AlreadyExistsException extends DdrsException {

	private static final long serialVersionUID = 8544316341382840270L;

	/**
	 * Constructs a new exception from the DDRS error.
	 * @param error the error from the service.
	 */
	public AlreadyExistsException(Error error) {
		super(error);
	}

}
