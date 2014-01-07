package com.digidata.services.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.digidata.services.rest.client.entities.Error;
import com.digidata.services.rest.client.exceptions.DdrsParseException;
import com.digidata.services.rest.client.exceptions.PropertyNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * Base class for all DDRS documents.
 * @author dan.sullivan
 *
 */
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
	
	/**
	 * Constructs a new, empty document.
	 */
	public Document() {
		properties = new HashMap<String, String>();
	}
	
	/**
	 * Gets the String value of a property by name.
	 * @param name the name of the property to get.
	 * @return the value of the property as a String.
	 * @throws PropertyNotFoundException if a property with the given name does not exist.
	 */
	protected String getProperty(String name){
		if(!properties.containsKey(name))
			throw new PropertyNotFoundException(name);
		
		return properties.get(name);
	}
	
	/**
	 * Gets the typed value of a property by name.
	 * @param name the name of the property to get.
	 * @param responseType the type of the property to get.
	 * @return the value of the property.
	 * @throws DdrsParseException if the value of the property cannot be converted to the given type.
	 */
	protected <T> T getProperty(String name, Class<T> responseType) {
		return Convert.parse(getProperty(name), responseType);
	}
	
	/**
	 * Sets the property with the name value pair.
	 * @param name the name of the property to set.
	 * @param value the value of the property.
	 */
	protected void setProperty(String name, String value) {
		properties.put(name, value);
	}
	
	/**
	 * Sets the property of the given type with the name value pair.
	 * @param name the name of the property to set.
	 * @param value the value of the property to set.
	 * @param responseType the type of the incoming property value.
	 */
	protected <T> void setProperty(String name, T value, Class<T> responseType) {
		setProperty(name, Convert.asString(value, responseType));
	}
	
	/**
	 * Gets the map of the document's properties.
	 * @return a map of all properties.
	 */
	public Map<String, String> getProperties() {
		return properties;
	}
	
	/**
	 * Sets the map of the document's properties.
	 * @param properties a map of all properties.
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	/**
	 * Gets the document's associations aspect.
	 * @return all associations
	 */
	public Associations getAssociations() {
		return associations;
	}
	
	/**
	 * Sets the document's associations aspect.
	 * @param associations
	 */
	public void setAssociations(Associations associations) {
		this.associations = associations;
	}
	
	/**
	 * Gets the rel value of the current document, if present.
	 * @return the rel value.
	 */
	public String getRel() {
		return rel;
	}
	
	/**
	 * Sets the rel value of the current document.
	 * @param rel the rel value.
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * Gets the doc value (indicating aspect type) of the current document.
	 * @return the doc value.
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Sets the doc value of the current document.
	 * @param doc the doc value.
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	/**
	 * Gets the 'self' {@link Link} associated with this document.
	 * @return the self link.
	 */
	@JsonIgnore
	public Link getSelf() {
		return associations.findLink("self");
	}
	
	/**
	 * Returns a friendly String representation of this document.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("doc", doc).toString();
	}
	
}
