package com.digidata.services.rest.client.examples;

import java.io.FileInputStream;
import java.io.IOException;

import com.digidata.services.rest.client.Collection;
import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.Link;
import com.digidata.services.rest.client.LinkTemplate;
import com.digidata.services.rest.client.entities.File;
import com.digidata.services.rest.client.entities.Folder;
import com.digidata.services.rest.client.entities.User;

public class FileUploadExample extends BaseRestExample {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute()  {
		
		User me = Ddrs.startFresh();
		
		java.io.File file = new java.io.File("C:\\tmp\\rest-test.txt");
		
		if(file.exists()) {
			println("Uploading file '%1$s' of size '%2$s'", file.getName(), file.getTotalSpace());
			
			try(FileInputStream stream = new FileInputStream(file)) {

				// Upload the file to the root folder with a random name
				String fileName = String.format("%1$s.txt", random());
				Folder root = me.getRoot();
				root.upload(fileName, stream);
				
				// Search for the new file in the root folder
				LinkTemplate children = root.getAssociations().findLinkTemplate("children");
				Link search = children.bind("searchString", fileName);
				Collection searchResult = search.get(Collection.class);
				File newFile = (File) searchResult.getMembers().get(0);
				
				println("File: %1$s", newFile);
				
			} catch (IOException e) {
				println("Failed to read file - %1$s", e.toString());
			}
		} else {
			println("File '%1$s' does not exist; aborting.", file.getPath());
		}
	}

}
