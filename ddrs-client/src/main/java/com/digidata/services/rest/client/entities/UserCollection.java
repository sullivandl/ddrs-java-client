package com.digidata.services.rest.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User collections represent an arbitrary grouping of users. These groupings 
 * may include businesses (Digidata), departments (Human Resources), or families 
 * (sub-accounts, allowing an individual to share his/her allocated space with others).
 *  
 * @author dan.sullivan
 *
 */
public class UserCollection extends CreatedEntity {

	/**
	 * Gets the arbitrary name of the user collection.
	 * @return the name.
	 */
	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	/**
	 * Sets the arbitrary name of the user collection.
	 * @param name the name.
	 */
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
}
