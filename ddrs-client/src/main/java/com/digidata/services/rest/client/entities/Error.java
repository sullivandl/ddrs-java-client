package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Error extends Document {

	@JsonIgnore
	public String getCode() {
		return getProperty("code");
	}
	
	@JsonIgnore
	public void setCode(String code) {
		setProperty("code", code);
	}
	
	@JsonIgnore
	public String getMessage() {
		return getProperty("message");
	}
	
	@JsonIgnore
	public void setMessage(String message) {
		setProperty("message", message);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				   .append("code", getCode())
				   .append("message", getMessage())
				   .toString();
	}
}
