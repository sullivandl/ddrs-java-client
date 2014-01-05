package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Share extends CreatedEntity {

	@JsonIgnore
	public boolean isPublic() {
		return getProperty("isPublic", Boolean.class);
	}
	
	@JsonIgnore
	public void setPubic(boolean isPublic) {
		setProperty("isPublic", isPublic, Boolean.class);
	}
	
	@JsonIgnore
	public boolean isReadOnly() {
		return getProperty("isReadOnly", Boolean.class);
	}
	
	@JsonIgnore
	public void setReadOnly(boolean isReadOnly) {
		setProperty("isReadOnly", isReadOnly, Boolean.class);
	}
	
	@JsonIgnore
	public String getRecipient() {
		return getProperty("recipient");
	}
	
	@JsonIgnore
	public void setRecipient(String recipient) {
		setProperty("recipient", recipient);
	}
	
	@JsonIgnore
	public String getUrl() {
		return getProperty("url");
	}
	
	@JsonIgnore
	public void setUrl(String url) {
		setProperty("url", url);
	}
}
