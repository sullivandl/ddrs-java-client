package com.digidata.services.rest.client.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.digidata.services.rest.client.LinkTemplate;
import com.digidata.services.rest.client.exceptions.AlreadyExistsException;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class for items (files and folders) in a file system.
 * 
 * Several convenience methods have been implemented to manage items in the file system.
 * @author dan.sullivan
 *
 */
public class FilesystemEntity extends CreatedEntity {

	/**
	 * Gets the name property of this entity.
	 * @return the name.
	 */
	@JsonIgnore
	public String getName() {
		return getProperty("name");
	}
	
	/**
	 * Sets the naem property of this entity.
	 * @param name the name.
	 */
	@JsonIgnore
	public void setName(String name) {
		setProperty("name", name);
	}
	
	/**
	 * Gets the original location if the item is in the user's trash.
	 * @return the original file path before delete.
	 */
	@JsonIgnore
	public String getOriginalLocation() {
		return getProperty("originalLocation");
	}
	
	/**
	 * Sets the original location property.
	 * @param originalLocation the original file path location.
	 */
	@JsonIgnore
	public void setOriginalLocation(String originalLocation) {
		setProperty("originalLocation", originalLocation);
	}
	
	/**
	 * Gets the date of delete if this item is in the user's trash. 
	 * @return the trashed date.
	 */
	@JsonIgnore
	public Date getTrashedDate() {
		return getProperty("trashedDate", Date.class);
	}
	
	/**
	 * Sets the trashed date property.
	 * @param trashedDate the trashed date.
	 */
	@JsonIgnore
	public void setTrashedDate(Date trashedDate) {
		setProperty("trashedDate", trashedDate, Date.class);
	}
	
	/**
	 * Gets whether or not this entity is shared.
	 * @return sharedness.
	 */
	@JsonIgnore
	public boolean isShared() {
		return Boolean.parseBoolean(getProperty("isShared"));
	}

	/**
	 * Sets whether or not this entity is shared.
	 * @param isShared sharedness.
	 */
	@JsonIgnore
	public void setShared(boolean isShared) {
		setProperty("isShared", String.valueOf(isShared));
	}
	
	/**
	 * DELETEs this entity from the file system. This is a <i>soft</i> delete, in
	 * that the item is moved to the user's trash.
	 */
	public void delete() {
		getSelf().delete();
	}
	
	/**
	 * Copies this entity to the destination folder. If an entity of the same
	 * name already exists in the destination folder an exception is thrown.
	 * @param destinationFolder the folder to copy to.
	 * @return the newly copied entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity copy(Folder destinationFolder) {
		return copy(destinationFolder, false);
	}
	
	/**
	 * Copies this entity to the destination folder. 
	 * @param destinationFolder the folder to copy to.
	 * @param overwrite whether or not to write an already existing entity in
	 * the destination folder.
	 * @return the newly copied entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name and overwrite=false.
	 */
	public FilesystemEntity copy(Folder destinationFolder, boolean overwrite) {
		return copy(destinationFolder.getId(), overwrite);
	}
	
	/**
	 * Copies this entity to the destination folder. If an entity of the same
	 * name already exists in the destination folder an exception is thrown.
	 * @param destinationFolderId the id of the folder to copy to.
	 * @return the newly copied entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity copy(String destinationFolderId) {
		return copy(destinationFolderId, false);
	}
	
	/**
	 * Copies this entity to the destination folder. 
	 * @param destinationFolderId the id of the folder to copy to.
	 * @param overwrite whether or not to write an already existing entity in
	 * the destination folder.
	 * @return the newly copied entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name and overwrite=false.
	 */
	public FilesystemEntity copy(String destinationFolderId, boolean overwrite) {
		return copier(destinationFolderId, false, overwrite);
	}
	
	/**
	 * Moves this entity to the destination folder. If an entity of the same
	 * name already exists in the destination folder an exception is thrown.
	 * @param destinationFolder the folder to move to.
	 * @return the newly moved entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity move(Folder destinationFolder) {
		return move(destinationFolder, false);
	}
	
	/**
	 * Moves this entity to the destination folder. 
	 * @param destinationFolder the folder to move to.
	 * @param overwrite whether or not to write an already existing entity in
	 * the destination folder.
	 * @return the newly moved entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity move(Folder destinationFolder, boolean overwrite) {
		return move(destinationFolder.getId(), overwrite);
	}
	
	/**
	 * Moves this entity to the destination folder. If an entity of the same
	 * name already exists in the destination folder an exception is thrown.
	 * @param destinationFolderId the id of the folder to move to.
	 * @return the newly moved entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity move(String destinationFolderId) {
		return move(destinationFolderId, false);
	}
	
	/**
	 * Moves this entity to the destination folder. 
	 * @param destinationFolderId the id of the folder to move to.
	 * @param overwrite whether or not to write an already existing entity in
	 * the destination folder.
	 * @return the newly moved entity.
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	public FilesystemEntity move(String destinationFolderId, boolean overwrite) {
		return copier(destinationFolderId, true, overwrite);
	}
	
	/**
	 * Returns a friendly representation of this file system entity.
	 * @return the string representation of this file system entity.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		           .appendSuper(super.toString())
		           .append("name", getName())
		           .toString();
	}
	
	/**
	 * A helper method to perform the move and copy operations.
	 * @param destinationFolderId the folder to copy/move to.
	 * @param deleteSource whether or not to delete the source (that'd be a move).
	 * @param overwrite whether or not to overwrite the destination conflict.
	 * @return the newly copied/moved entity
	 * @throws AlreadyExistsException if the destination folder already contains an
	 * entity of the same name.
	 */
	private FilesystemEntity copier(String destinationFolderId, boolean deleteSource, boolean overwrite) {
		LinkTemplate template = getAssociations().findLinkTemplate("copier");
		Map<String, String> bindings = new HashMap<String, String>();
		bindings.put("destinationFolder", destinationFolderId);
		bindings.put("overwrite", (overwrite? "1" : "0"));
		bindings.put("deleteSource", (deleteSource? "1" : "0"));
		
		return template.bind(bindings).post(FilesystemEntity.class, this.isCompact());
	}
}
