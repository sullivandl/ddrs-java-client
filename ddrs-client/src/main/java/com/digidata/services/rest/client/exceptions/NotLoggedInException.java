package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

/**
 * This exception is thrown when the user has not logged in to 
 * the service.
 * 
 * @author dan.sullivan
 *
 */
public class NotLoggedInException extends DdrsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception from the DDRS error.
	 * @param error the error from the service.
	 */
	public NotLoggedInException(Error error) {
		super(error);
	}

}
