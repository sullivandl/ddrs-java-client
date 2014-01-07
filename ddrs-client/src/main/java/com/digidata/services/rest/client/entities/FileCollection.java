package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * File Collections group files together, primarily to facilitate sharing
 * a group of files without requiring that those files be located in the
 * same folder. File collections are useful in making, for example, photo
 * albums and media playlists.

 * @author dan.sullivan
 *
 */
public class FileCollection extends CreatedEntity {

	/**
	 * Gets the name of the file collection.
	 * @return the name.
	 */
	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	/**
	 * Sets the name of the file collection.
	 * @param name the name.
	 */
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
}
