package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

public class DdrsException extends RuntimeException {

	private static final long serialVersionUID = 5915840038718459244L;

	private Error error;
	
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
	
	public DdrsException(Error error) {
		super(String.format("Error with code '%s' occurred", error.getCode()));
		this.error = error;
	}
	
	public Error getError() {
		return error;
	}
}
