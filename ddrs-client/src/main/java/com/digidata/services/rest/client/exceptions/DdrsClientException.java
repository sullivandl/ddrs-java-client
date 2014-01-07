package com.digidata.services.rest.client.exceptions;

/**
 * This exception is thrown when the Ddrs client side library is not used correctly.
 * Service side errors generate a {@link DdrsException}.
 * 
 * @author dan.sullivan
 *
 */
public class DdrsClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public DdrsClientException() {
		super();
	}
	
	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public DdrsClientException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public DdrsClientException(Throwable inner) {
		super(inner);
	}
	
	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public DdrsClientException(String message, Throwable inner) {
		super(message, inner);
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
	 */
	public DdrsClientException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
