package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to represent error documents. Errors are returned by the server
 * along with an appropriate HTTP error status code.
 * 
 * @author dan.sullivan
 *
 */
public class Error extends Document {

	/**
	 * Gets the DDRS error status code.
	 * @return the code.
	 */
	@JsonIgnore
	public String getCode() {
		return getProperty("code");
	}
	
	/**
	 * Sets the DDRS error status code.
	 * @param code
	 */
	@JsonIgnore
	public void setCode(String code) {
		setProperty("code", code);
	}
	
	/**
	 * Gets the DDRS error message.
	 * @return the message.
	 */
	@JsonIgnore
	public String getMessage() {
		return getProperty("message");
	}
	
	/**
	 * Sets the DDRS error message.
	 * @param message the message.
	 */
	@JsonIgnore
	public void setMessage(String message) {
		setProperty("message", message);
	}
	
	/**
	 * Gets whether or not this DDRS message can be displayed to users. 
	 * @return the friendliness
	 */
	public boolean isUserFriendly() {
		return getProperty("userFriendly", Boolean.class);
	}

	/**
	 * Sets whether or not this DDRS message can be displayed to users. 
	 * @param userFriendly the friendliness
	 */
	public void setUserFriendly(boolean userFriendly) {
		setProperty("userFriendly", userFriendly, Boolean.class);
	}
	
	/**
	 * Gets the DDRS error detail. Often includes server side stack trace.
	 * @return the detail.
	 */
	public String getDetail() {
		return getProperty("detail");
	}

	/**
	 * Sets the DDRS error detail. 
	 * @param detail the detail.
	 */
	public void setDetail(String detail) {
		setProperty("detail", detail);
	}
	
	/**
	 * Returns a friendly representation of this error.
	 * @return the string representation of this error.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				   .append("code", getCode())
				   .append("message", getMessage())
				   .toString();
	}
}
