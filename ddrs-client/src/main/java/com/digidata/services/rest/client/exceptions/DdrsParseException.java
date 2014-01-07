package com.digidata.services.rest.client.exceptions;

/**
 * This exception is thrown when an entity's property cannot be parsed
 * on the client side.
 * 
 * @author dan.sullivan
 *
 */
public class DdrsParseException extends DdrsClientException {

	private static final long serialVersionUID = 1L;

	/**
	 * @see DdrsClientException#DdrsClientException()
	 */
	public DdrsParseException() {
	}

	/**
	 * @see DdrsClientException#DdrsClientException(String)
	 */
	public DdrsParseException(String message) {
		super(message);
	}

	/**
	 * @see DdrsClientException#DdrsClientException(Throwable)
	 */
	public DdrsParseException(Throwable inner) {
		super(inner);
	}

	/**
	 * @see DdrsClientException#DdrsClientException(String, Throwable)
	 */
	public DdrsParseException(String message, Throwable inner) {
		super(message, inner);
	}

	/**
	 * @see DdrsClientException#DdrsClientException(String, Throwable, boolean, boolean)
	 */
	public DdrsParseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
