package com.digidata.services.rest.client.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends CreatedEntity {

	@JsonIgnore
	public String getLogin() {
		return getProperty("login");
	}
	
	@JsonIgnore
	public void setLogin(String login) {
		setProperty("login", login);
	}
	
	@JsonIgnore
	public String getEmailAddress() {
		return getProperty("emailAddress");
	}
	
	@JsonIgnore
	public void setEmailAddress(String emailAddress) {
		setProperty("emailAddress", emailAddress);
	}
	
	@JsonIgnore
	public String getFirstName() {
		return getProperty("firstName");
	}
	
	@JsonIgnore
	public void setFirstName(String firstName) {
		setProperty("firstName", firstName);
	}
	
	@JsonIgnore
	public String getLastName() {
		return getProperty("lastName");
	}
	
	@JsonIgnore
	public void setLastName(String lastName) {
		setProperty("lastName", lastName);
	}
	
	@JsonIgnore
	public String getLocale() {
		return getProperty("locale");
	}
	
	@JsonIgnore
	public void setLocale(String locale) {
		setProperty("locale", locale);
	}
	
	@JsonIgnore
	public double getSpaceTotal() {
		return getProperty("spaceTotal", Double.class)	;
	}
	
	@JsonIgnore
	public void setSpaceTotal(double spaceTotal) {
		setProperty("spaceTotal", spaceTotal, Double.class);
	}
	
	@JsonIgnore
	public double getSpaceAvailable() {
		return getProperty("spaceAvailable", Double.class);
	}
	
	@JsonIgnore
	public void setSpaceAvailable(double spaceAvailable) {
		setProperty("spaceAvailable", spaceAvailable, Double.class);
	}
	
	@JsonIgnore
	public Folder getRoot() {
		Link root = this.getAssociations().findLink("root");
		return root.get(Folder.class);
	}
	
	@JsonIgnore
	public Folder getBackups() {
		Link backups = this.getAssociations().findLink("root.backups");
		return backups.get(Folder.class);
	}
	
	@JsonIgnore
	public Folder getTrash() {
		Link trash = this.getAssociations().findLink("root.trash");
		return trash.get(Folder.class);
	}
	
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
