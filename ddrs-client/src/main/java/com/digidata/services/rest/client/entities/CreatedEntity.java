package com.digidata.services.rest.client.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class CreatedEntity extends IdEntity {
	
	@JsonIgnore
	public Date getCreated() {
		return getProperty("created", Date.class);
	}
	
	@JsonIgnore
	public void setCreated(Date created) {
		setProperty("created", created, Date.class);
	}

}
