package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCollection extends CreatedEntity {

	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
}
