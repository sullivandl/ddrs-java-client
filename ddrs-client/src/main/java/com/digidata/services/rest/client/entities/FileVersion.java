package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * File version entities represent whole revisions of a file.
 * 
 * @author dan.sullivan
 *
 */
public class FileVersion extends CreatedEntity {

	/**
	 * Gets the size, in bytes, of the file.
	 * @return the size.
	 */
	@JsonIgnore
	public double getSize() {
		return getProperty("size", Double.class);
	}
	
	/**
	 * Sets the size, in bytes, of the file.
	 * @param size the size.
	 */
	@JsonIgnore
	public void setSize(double size) {
		setProperty("name", size, Double.class);
	}
}
