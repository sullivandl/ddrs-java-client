package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * Tags may be applied to any entity in the system. They are namespace-
 * qualified name-value pairs that contain arbitrary metadata related to
 * the entity. Tags are generally client-driven metadata that doesn’t fit
 * as a first-order property of that entity, but must nonetheless be
 * associated with it.
 * 
 * @author dan.sullivan
 *
 */
public class Tag extends CreatedEntity {

	/**
	 * Gets the namespace of this tag. Namespaces qualify the accompanying
	 * name property in order to void collisions between the same name being
	 * used in different contexts. When this element is missing, the name MUST
	 * be assumed to bin the global namespace.
	 * @return the namespace
	 */
	@JsonIgnore
	public String getNamespace() {
		return getProperty("namespace");
	}
	
	/**
	 * Sets the namespace property.
	 * @param namespace the namespace.
	 */
	@JsonIgnore
	public void setNamespace(String namespace) {
		setProperty("namespace", namespace);
	}
	
	/**
	 * Gets the name of the tag. 
	 * @return the name.
	 */
	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	/**
	 * Sets the name of the tag.
	 * @param name the name.
	 */
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
	
	/**
	 * Gets the arbitrary string value associated with this tag.
	 * @return the value.
	 */
	@JsonIgnore
	public String getValue() {
		return getProperty("value");
	}
	
	/**
	 * Sets the arbitrary string value associated with this tag.
	 * @param value the value.
	 */
	@JsonIgnore
	public void setValue(String value) {
		setProperty("Value", value);
	}

	/**
	 * Gets the fully qualified name of the property by concatenating
	 * the namespace property and name property separated by a '.' character.
	 * @return the fully qualified name.
	 */
	@JsonIgnore
	public String getQualifiedName() {
		if(StringUtils.isEmpty(getNamespace())) 
			return getName();
		else
			return getNamespace() + "." + getName();
	}
	
	/**
	 * Returns a friendly representation of this tag.
	 * @return the string representation of this tag.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.appendSuper(super.toString())
					.append("qualifiedName", getQualifiedName())
					.append("value", getValue())
					.toString();
	}
}
