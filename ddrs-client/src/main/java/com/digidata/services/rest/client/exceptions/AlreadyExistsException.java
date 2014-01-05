package com.digidata.services.rest.client.exceptions;

import com.digidata.services.rest.client.entities.Error;

public class AlreadyExistsException extends DdrsException {

	private static final long serialVersionUID = 8544316341382840270L;

	public AlreadyExistsException(Error error) {
		super(error);
	}

}
