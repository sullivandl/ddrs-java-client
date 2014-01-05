package com.digidata.services.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.digidata.services.rest.client.entities.Contact;
import com.digidata.services.rest.client.entities.File;
import com.digidata.services.rest.client.entities.FileCollection;
import com.digidata.services.rest.client.entities.FileVersion;
import com.digidata.services.rest.client.entities.Folder;
import com.digidata.services.rest.client.entities.Share;
import com.digidata.services.rest.client.entities.Tag;
import com.digidata.services.rest.client.entities.User;
import com.digidata.services.rest.client.entities.UserCollection;
import com.digidata.services.rest.client.exceptions.DdrsClientException;
import com.digidata.services.rest.client.exceptions.RelNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.PROPERTY, property="type", defaultImpl=Entity.class)
@JsonSubTypes({
	@JsonSubTypes.Type(value=User.class, name="user"),
	@JsonSubTypes.Type(value=Contact.class, name="contact"),
	@JsonSubTypes.Type(value=Folder.class, name="folder"),
	@JsonSubTypes.Type(value=File.class, name="file"),
	@JsonSubTypes.Type(value=FileCollection.class, name="file-collection"),
	@JsonSubTypes.Type(value=FileVersion.class, name="file-version"),
	@JsonSubTypes.Type(value=Share.class, name="share"),
	@JsonSubTypes.Type(value=Tag.class, name="tag"),
	@JsonSubTypes.Type(value=UserCollection.class, name="user-colleciton")
})
public class Entity extends Document {
	
	private String type;
	private boolean compact;
	private List<Entity> neighbors;		// TODO: figure out what we need to do here
	private List<Collection> collections;
	
	public Entity() {
		neighbors = new ArrayList<Entity>();
		collections = new ArrayList<Collection>();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isCompact() {
		return compact;
	}
	public void setCompact(boolean compact) {
		this.compact = compact;
	}
	public List<Entity> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(List<Entity> neighbors) {
		this.neighbors = neighbors;
	}
	public List<Collection> getCollections() {
		return collections;
	}
	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}
	
	public Collection findCollection(String rel) {
		for(Collection c : collections) {
			if(c.getRel().equals(rel))
				return c;
		}
		throw new RelNotFoundException(rel);
	}
	
	public Collection findCollectionOrNull(String rel) {
		try {
			return findCollection(rel);
		} catch(DdrsClientException ex) {
			return null;
		}
	}
}
