package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

public class NotLoggedInException extends DdrsException {

	private static final long serialVersionUID = 1L;

	public NotLoggedInException(Error error) {
		super(error);
	}

}
