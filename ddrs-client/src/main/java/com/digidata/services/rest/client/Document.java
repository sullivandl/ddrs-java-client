package com.digidata.services.rest.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.digidata.services.rest.client.entities.Error;
import com.digidata.services.rest.client.exceptions.PropertyNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonIgnoreProperties(ignoreUnknown=false)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.PROPERTY, property="doc", defaultImpl=Document.class)
@JsonSubTypes({
	@JsonSubTypes.Type(value=Entity.class, name="entity"),
	@JsonSubTypes.Type(value=Collection.class, name="collection"),
	@JsonSubTypes.Type(value=Error.class, name="error")
})
public class Document extends DdrsElement {

	private Map<String, String> properties;
	private Associations associations;
	private String rel;
	private String doc;
	
	public Document() {
		properties = new HashMap<String, String>();
	}
	
	protected String getProperty(String name){
		if(!properties.containsKey(name))
			throw new PropertyNotFoundException(name);
		
		return properties.get(name);
	}
	
	protected <T> T getProperty(String name, Class<T> responseType) {
		return Convert.parse(getProperty(name), responseType);
	}
	
	protected void setProperty(String name, String value) {
		properties.put(name, value);
	}
	
	protected <T> void setProperty(String name, T value, Class<T> responseType) {
		setProperty(name, Convert.asString(value, responseType));
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	public Associations getAssociations() {
		return associations;
	}
	public void setAssociations(Associations associations) {
		this.associations = associations;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	@JsonIgnore
	public Link getSelf() {
		List<Link> links = associations.getLinks();
		for(Link link : links)
			if(link.getRel().equals("self"))
				return link;
		return null;	// Do better!
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("doc", doc).toString();
	}
	
}
