package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Shares are created by users of the system in order to provide others
 * access to a single file, file collection or folder Shares may be offered
 * to a specific contact, a collection of contacts, or made publicly available.
 * 
 * @author dan.sullivan
 *
 */
public class Share extends CreatedEntity {

	/**
	 * Gets whether or not this share is public. Instead of providing
	 * access for a specific recipient, a share may be created to provide
	 * public access to a resource.  When (and only when) the value of
	 * this property is true, access to the shared resource is not restricted
	 * by access control.
	 * @return the public shareness
	 */
	@JsonIgnore
	public boolean isPublic() {
		return getProperty("isPublic", Boolean.class);
	}
	
	/**
	 * Sets the isPublic property.
	 * @param isPublic the public shareness.
	 */
	@JsonIgnore
	public void setPubic(boolean isPublic) {
		setProperty("isPublic", isPublic, Boolean.class);
	}
	
	/**
	 * Gets whether or not this share is readonly. When the value of this
	 * property is true, write access to the shared resource by the associated
	 * party will always be denied; otherwise, write access will be allowed.
	 * Defaults to {@code true}.
	 * @return the readonlyness.
	 */
	@JsonIgnore
	public boolean isReadOnly() {
		return getProperty("isReadOnly", Boolean.class);
	}
	
	/**
	 * Sets the isReadOnly property.
	 * @param isReadOnly the readonlyness.
	 */
	@JsonIgnore
	public void setReadOnly(boolean isReadOnly) {
		setProperty("isReadOnly", isReadOnly, Boolean.class);
	}
	
	/**
	 * Gets the unique identifier of the item that is being shared. Once
	 * a share is created, this property may not be changed.
	 * @return the item id.
	 */
	@JsonIgnore
	public String getItem() {
		return getProperty("item");
	}
	
	/**
	 * Sets the unique identifier of the item that is being shraed.
	 * @param item the item id.
	 */
	@JsonIgnore
	public void setItem(String item) {
		setProperty("item", item);
	}
	
	/**
	 * Gets the unique identifier of the entity to which access should be
	 * granted.  Once a share is created, this property may not be changed. If
	 * this is a {@link #isPublic()} share this property will be blank.
	 * @return the recipient id.
	 */
	@JsonIgnore
	public String getRecipient() {
		return getProperty("recipient");
	}
	
	/**
	 * Sets the id of the recipient id to which the item is shared.
	 * @param recipient the recipient id.
	 */
	@JsonIgnore
	public void setRecipient(String recipient) {
		setProperty("recipient", recipient);
	}
	
	/**
	 * Gets the publicly accessible URL if this share {@link Share#isPublic()}.
	 * @return the public url.
	 */
	@JsonIgnore
	public String getUrl() {
		return getProperty("url");
	}
	
	/**
	 * Sets the publicly accessible URL.
	 * @param url the public url.
	 */
	@JsonIgnore
	public void setUrl(String url) {
		setProperty("url", url);
	}
	
	/**
	 * Returns a friendly representation of this share.
	 * @return the string representation of this share.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("isPublic", isPublic())
				.append("item", getItem())
				.append("recipient", getRecipient())
				.toString();
	}
}
