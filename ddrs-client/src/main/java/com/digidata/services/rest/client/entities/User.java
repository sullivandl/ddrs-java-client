package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Users represent the primary human consumers of the service. They have
 * space allotted to them over which they have full control, and they can
 * share the content of that space with others at will.
 * 
 * @author dan.sullivan
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends CreatedEntity {

	/**
	 * Gets the unique login name of this user.
	 * @return the login.
	 */
	@JsonIgnore
	public String getLogin() {
		return getProperty("login");
	}
	
	/**
	 * Sets the unique login name of this user.
	 * @param login the login.
	 */
	@JsonIgnore
	public void setLogin(String login) {
		setProperty("login", login);
	}
	
	/**
	 * Gets the email address of this user. Email addresses
	 * must be unique across all users in the system.
	 * @return the email address.
	 */
	@JsonIgnore
	public String getEmailAddress() {
		return getProperty("emailAddress");
	}
	
	/**
	 * Sets the email address of this user.
	 * @param emailAddress the email address.
	 */
	@JsonIgnore
	public void setEmailAddress(String emailAddress) {
		setProperty("emailAddress", emailAddress);
	}
	
	/**
	 * Gets the first name of this user.
	 * @return the first name.
	 */
	@JsonIgnore
	public String getFirstName() {
		return getProperty("firstName");
	}
	
	/**
	 * Sets the first name of this user.
	 * @param firstName the first name.
	 */
	@JsonIgnore
	public void setFirstName(String firstName) {
		setProperty("firstName", firstName);
	}
	
	/**
	 * Gets the last name of this user.
	 * @return the last name.
	 */
	@JsonIgnore
	public String getLastName() {
		return getProperty("lastName");
	}
	
	/**
	 * Sets the last name of this user.
	 * @param lastName the last name.
	 */
	@JsonIgnore
	public void setLastName(String lastName) {
		setProperty("lastName", lastName);
	}
	
	/**
	 * Gets the user's preferred locale. If a user's preferred locale
	 * has not been specified, this property defaults to the preferred
	 * locale of the system.
	 * @return the locale.
	 */
	@JsonIgnore
	public String getLocale() {
		return getProperty("locale");
	}
	
	/**
	 * Sets the user's preferred locale.
	 * @param locale the locale.
	 */
	@JsonIgnore
	public void setLocale(String locale) {
		setProperty("locale", locale);
	}
	
	/**
	 * Gets the total number of bytes allocated to this user.
	 * @return the space total.
	 */
	@JsonIgnore
	public double getSpaceTotal() {
		return getProperty("spaceTotal", Double.class)	;
	}
	
	/**
	 * Sets the total number of bytes allocated to this user.
	 * @param spaceTotal the space total.
	 */
	@JsonIgnore
	public void setSpaceTotal(double spaceTotal) {
		setProperty("spaceTotal", spaceTotal, Double.class);
	}
	
	/**
	 * Gets the total number of bytes the user may currently consume.
	 * @return the space available.
	 */
	@JsonIgnore
	public double getSpaceAvailable() {
		return getProperty("spaceAvailable", Double.class);
	}
	
	/**
	 * Sets the total number of bytes available to the user.
	 * @param spaceAvailable the space available.
	 */
	@JsonIgnore
	public void setSpaceAvailable(double spaceAvailable) {
		setProperty("spaceAvailable", spaceAvailable, Double.class);
	}
	
	/**
	 * Gets the total number of bytes the user has already consumed.
	 * This is simply {@link #getSpaceTotal()} minus {@link #getSpaceAvailable()}.
	 * @return the space consumed.
	 */
	@JsonIgnore
	public double getSpaceConsumed() {
		return getSpaceTotal() - getSpaceAvailable();
	}
	
	/**
	 * Convenience method to get the user's 'root' folder.
	 * @return the root folder.
	 */
	@JsonIgnore
	public Folder getRoot() {
		Link root = this.getAssociations().findLink("root");
		return root.get(Folder.class);
	}
	
	/**
	 * Convenience method to get the user's 'root.backups' folder.
	 * @return the backups folder.
	 */
	@JsonIgnore
	public Folder getBackups() {
		Link backups = this.getAssociations().findLink("root.backups");
		return backups.get(Folder.class);
	}
	
	/**
	 * Convenience method to get the user's 'root.trash' folder.
	 * @return the trash folder.
	 */
	@JsonIgnore
	public Folder getTrash() {
		Link trash = this.getAssociations().findLink("root.trash");
		return trash.get(Folder.class);
	}
	
	/**
	 * Returns a friendly representation of this user.
	 * @return the string representation of this user.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
		                            .append("login", getLogin())
				                    .append("emailAddress", getEmailAddress())
				                    .append("firstName", getFirstName())
				                    .append("lastName", getLastName())
				                    .append("spaceTotal", getSpaceTotal())
				                    .toString();
	}
}
