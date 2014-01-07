package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Users maintain a list of contacts with whom they can share files and
   folders. Contacts are simple structures that contain data used to
   identify, for example, a friend or co-worker.
   
 * @author dan.sullivan
 *
 */
public class Contact extends IdEntity {

	/**
	 * Gets the contact's email address property. Email addresses must
	 * be unique across all of the user's contacts.
	 * @return the email address.
	 */
	@JsonIgnore
	public String getEmailAddress() {
		return getProperty("emailAddress");
	}
	
	/**
	 * Sets the contact's email address property.
	 * @param value the email address.
	 */
	@JsonIgnore
	public void setEmailAddress(String value) {
		setProperty("emailAddress", value);
	}
	
	/**
	 * Gets the contact's first name property.
	 * @return the first name.
	 */
	@JsonIgnore
	public String getFirstName() {
		return getProperty("firstName");
	}
	
	/**
	 * Sets the contact's first name property.
	 * @param value
	 */
	@JsonIgnore
	public void setFirstName(String value) {
		setProperty("firstName", value);
	}
	
	/**
	 * Gets the contact's last name property.
	 * @return the last name.
	 */
	@JsonIgnore
	public String getLastName() {
		return getProperty("lastName");
	}
	
	/**
	 * Sets the contact's last name property.
	 * @param value the last name.
	 */
	@JsonIgnore
	public void setLastName(String value) {
		setProperty("lastName", value);
	}
	
	/**
	 * Returns a friendly representation of this contact.
	 * @return the string representation of this contact.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.appendSuper(super.toString())
					.append("emailAddress", getEmailAddress())
					.append("firstName", getFirstName())
					.append("lastName", getLastName())
					.toString();
	}
}
