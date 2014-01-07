package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

/**
 * This Exception is thrown when a error is returned by the service. The
 * various sub-classes represent known error conditions.  Additional error information
 * may be retrieved from this class' {@link #getError()} entity.
 * 
 * @author dan.sullivan
 *
 */
public class DdrsException extends RuntimeException {

	private static final long serialVersionUID = 5915840038718459244L;

	private Error error;
	
	/**
	 * Construct a DdrsException based on the DDRS  error.
	 * @param error the error from the service.
	 * @return the type-specific exception.
	 */
	public static DdrsException New(Error error) {
		String code = error.getCode();
		
		DdrsException exception = null;
		switch(code) {
			case "EntityAlreadyExists":
				exception = new AlreadyExistsException(error);
				break;
			case "MissingUser":
			case "NotLoggedIn":
				exception = new NotLoggedInException(error);
				break;
			default:
				exception = new DdrsException(error);
		}
		
		return exception;
	}
	
	/**
	 * Constructs a new DdrsException from the DDRS error.
	 * @param error the error from the service.
	 */
	public DdrsException(Error error) {
		super(String.format("Error with code '%s' occurred", error.getCode()));
		this.error = error;
	}
	
	/**
	 * Gets the associated error document returned by the service.
	 * @return the error.
	 */
	public Error getError() {
		return error;
	}
}
