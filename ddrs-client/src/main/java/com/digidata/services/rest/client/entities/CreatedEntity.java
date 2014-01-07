package com.digidata.services.rest.client.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class for all entities with a created date.
 * 
 * @author dan.sullivan
 *
 */
public class CreatedEntity extends IdEntity {
	
	/**
	 * Gets this entity's created date.
	 * @return the created date.
	 */
	@JsonIgnore
	public Date getCreated() {
		return getProperty("created", Date.class);
	}
	
	/**
	 * Sets this entity's created date.
	 * @param created the created date.
	 */
	@JsonIgnore
	public void setCreated(Date created) {
		setProperty("created", created, Date.class);
	}

}
