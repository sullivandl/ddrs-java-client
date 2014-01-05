package com.digidata.services.rest.client.exceptions;

public class DdrsClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DdrsClientException() {
		super();
	}
	
	public DdrsClientException(String message) {
		super(message);
	}

	public DdrsClientException(Throwable inner) {
		super(inner);
	}
	
	public DdrsClientException(String message, Throwable inner) {
		super(message, inner);
	}

	public DdrsClientException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
