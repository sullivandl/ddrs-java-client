package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class IdEntity extends Entity {

	@JsonIgnore
	public String getId() {
		return getProperty("id");
	}
	
	@JsonIgnore
	public void setId(String id) {
		setProperty("id", id);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", getId()).toString();
	}
}
