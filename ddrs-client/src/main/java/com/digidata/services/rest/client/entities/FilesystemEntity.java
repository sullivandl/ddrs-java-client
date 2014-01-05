package com.digidata.services.rest.client.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.LinkTemplate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FilesystemEntity extends CreatedEntity {

	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
	
	@JsonIgnore
	public String getOriginalLocation() {
		return getProperty("originalLocation");
	}
	
	@JsonIgnore
	public void setOriginalLocation(String originalLocation) {
		setProperty("originalLocation", originalLocation);
	}
	
	@JsonIgnore
	public Date getTrashedDate() {
		return getProperty("trashedDate", Date.class);
	}
	
	@JsonIgnore
	public void setTrashedDate(Date trashedDate) {
		setProperty("trashedDate", trashedDate, Date.class);
	}
	
	@JsonIgnore
	public boolean isShared() {
		return Boolean.parseBoolean(getProperty("isShared"));
	}

	@JsonIgnore
	public void setShared(boolean isShared) {
		setProperty("isShared", String.valueOf(isShared));
	}
	
	public void delete() {
		getSelf().delete();
	}
	
	public FilesystemEntity copy(Folder destinationFolder) {
		return copy(destinationFolder, false);
	}
	
	public FilesystemEntity copy(Folder destinationFolder, boolean overwrite) {
		return copy(destinationFolder.getId(), overwrite);
	}
	
	public FilesystemEntity copy(String destinationFolderId) {
		return copy(destinationFolderId, false);
	}
	
	public FilesystemEntity copy(String destinationFolderId, boolean overwrite) {
		return copier(destinationFolderId, false, overwrite);
	}
	
	public FilesystemEntity move(Folder destinationFolder) {
		return move(destinationFolder, false);
	}
	
	public FilesystemEntity move(Folder destinationFolder, boolean overwrite) {
		return move(destinationFolder.getId(), overwrite);
	}
	
	public FilesystemEntity move(String destinationFolderId) {
		return move(destinationFolderId, false);
	}
	
	public FilesystemEntity move(String destinationFolderId, boolean overwrite) {
		return copier(destinationFolderId, true, overwrite);
	}
	
	private FilesystemEntity copier(String destinationFolderId, boolean deleteSource, boolean overwrite) {
		LinkTemplate template = getAssociations().findLinkTemplate("copier");
		Map<String, String> bindings = new HashMap<String, String>();
		bindings.put("destinationFolder", destinationFolderId);
		bindings.put("overwrite", (overwrite? "1" : "0"));
		bindings.put("deleteSource", (deleteSource? "1" : "0"));
		
		return template.bind(bindings).post(FilesystemEntity.class, this.isCompact());
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		           .appendSuper(super.toString())
		           .append("name", getName())
		           .toString();
	}
}
