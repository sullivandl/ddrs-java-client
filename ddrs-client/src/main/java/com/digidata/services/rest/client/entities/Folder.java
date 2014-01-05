package com.digidata.services.rest.client.entities;

import java.io.InputStream;

import com.digidata.services.rest.client.Link;

public class Folder extends FilesystemEntity {

	public void upload(String fileName, InputStream stream) {
		this.getAssociations()
		    .findLinkTemplate("uploader")
		    .bind("newName", fileName)
		    .put(stream);
	}
	
	public Folder mkdir(String folderName) {
		Link children = this.getAssociations().findLink("children");
		Folder folder = new Folder();
		folder.setName(folderName);
		
		return children.post(folder, Folder.class, this.isCompact());
	}
}
