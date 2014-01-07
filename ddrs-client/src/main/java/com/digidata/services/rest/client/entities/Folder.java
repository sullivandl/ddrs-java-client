package com.digidata.services.rest.client.entities;

import java.io.InputStream;

import com.digidata.services.rest.client.Link;

/**
 * Folders contain files and other folders, resulting in a hierarchical
 * storage structure.
 * 
 * @author dan.sullivan
 *
 */
public class Folder extends FilesystemEntity {

	/**
	 * Convenience method to upload a file with a given name to this
	 * Folder.
	 * @param fileName the name of the file.
	 * @param stream the data of the file.
	 */
	public void upload(String fileName, InputStream stream) {
		this.getAssociations()
		    .findLinkTemplate("uploader")
		    .bind("newName", fileName)
		    .put(stream);
	}
	

	/**
	 * Convenience method to create a new sub-folder in this folder.
	 * @param folderName the name of the sub-folder.
	 * @return the newly created sub-folder.
	 */
	public Folder mkdir(String folderName) {
		Link children = this.getAssociations().findLink("children");
		Folder folder = new Folder();
		folder.setName(folderName);
		
		return children.post(folder, Folder.class, this.isCompact());
	}
}
