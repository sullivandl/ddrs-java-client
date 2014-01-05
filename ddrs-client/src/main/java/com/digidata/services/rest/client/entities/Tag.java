package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tag extends CreatedEntity {

	@JsonIgnore
	public String getNamespace() {
		return getProperty("namespace");
	}
	
	@JsonIgnore
	public void setNamespace(String namespace) {
		setProperty("namespace", namespace);
	}
	
	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
	
	@JsonIgnore
	public String getValue() {
		return getProperty("value");
	}
	
	@JsonIgnore
	public void setValue(String value) {
		setProperty("Value", value);
	}
	
}
