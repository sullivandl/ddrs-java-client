package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Contact extends IdEntity {

	@JsonIgnore
	public String getEmailAddress() {
		return getProperty("emailAddress");
	}
	
	@JsonIgnore
	public void setEmailAddress(String value) {
		setProperty("emailAddress", value);
	}
	
	@JsonIgnore
	public String getFirstName() {
		return getProperty("firstName");
	}
	
	@JsonIgnore
	public void setFirstName(String value) {
		setProperty("firstName", value);
	}
	
	@JsonIgnore
	public String getLastName() {
		return getProperty("lastName");
	}
	
	@JsonIgnore
	public void setLastName(String value) {
		setProperty("lastName", value);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.appendSuper(super.toString())
					.append("emailAddress", getEmailAddress())
					.append("firstName", getFirstName())
					.append("lastName", getLastName()).toString();
	}
}
