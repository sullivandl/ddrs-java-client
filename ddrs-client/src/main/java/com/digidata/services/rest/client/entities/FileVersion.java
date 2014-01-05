package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileVersion extends CreatedEntity {

	@JsonIgnore
	public double getSize() {
		return getProperty("size", Double.class);
	}
	
	@JsonIgnore
	public void setSize(double size) {
		setProperty("name", size, Double.class);
	}
}
