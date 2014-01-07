package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class for all documents with an id property.
 * 
 * @author dan.sullivan
 *
 */
public class IdEntity extends Entity {

	/**
	 * Gets the entity's unique id property.
	 * @return the id.
	 */
	@JsonIgnore
	public String getId() {
		return getProperty("id");
	}
	
	/**
	 * Sets the entity's unique id property.
	 * @param id the id property.
	 */
	@JsonIgnore
	public void setId(String id) {
		setProperty("id", id);
	}
	
	/**
	 * Returns a friendly representation of this entity.
	 * @return the string representation of this entity.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", getId())
				.toString();
	}
}
