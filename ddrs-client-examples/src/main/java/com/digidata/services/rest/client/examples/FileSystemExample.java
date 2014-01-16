package com.digidata.services.rest.client.examples;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.digidata.services.rest.client.Collection;
import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.Entity;
import com.digidata.services.rest.client.Link;
import com.digidata.services.rest.client.entities.File;
import com.digidata.services.rest.client.entities.Folder;
import com.digidata.services.rest.client.entities.User;

/**
 * This example performs simple file system operations.  All operations can be performed with
 * the RESTful {@link Link#put(Entity, Class)}, {@link Link#get(Class)}, etc. methods.  Here,
 * we are illustrating the available 'convenience' methods on the {@link File} and {@link Folder}
 * classes.
 * 
 * @author dan.sullivan
 *
 */
public class FileSystemExample extends BaseRestExample {

	private final InputStream EMPTY_STREAM = new ByteArrayInputStream(new byte[] {});
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		User me = Ddrs.startFresh(true);

		// Create a folder with a random name
		String topLevelFolderName = random();
		println("> mkdir %1$s", topLevelFolderName);
		Folder topLevelFolder = me.getRoot().mkdir(topLevelFolderName);
		
		ls(topLevelFolder);
		
		// Upload a few zero byte files to that folder.
		for(int x = 0; x < 5; x++) {
			String subFileName = random() + ".txt";
			println("> touch %s", subFileName);
			topLevelFolder.upload(subFileName, EMPTY_STREAM);
		}
		
		topLevelFolder = refresh(topLevelFolder);
		ls(topLevelFolder);
		
		// Create a few sub-folders in that folder.
		for(int x = 0; x < 5; x++) {
			String subFolderName = random();
			println("> mkdir %s", subFolderName);
			topLevelFolder.mkdir(subFolderName);
		}
		
		topLevelFolder = refresh(topLevelFolder);
		ls(topLevelFolder);

		// Create a well known folder named 'sub-folder'
		String subFolderName = "sub-folder";
		println("> mkdir %s", subFolderName);
		Folder subFolder = topLevelFolder.mkdir(subFolderName);
		
		// Copy one of the files to 'sub-folder'
		File fileToCopy = firstFile(topLevelFolder);
		println("> cp %1$s %2$s", fileToCopy.getName(), subFolder.getName());
		fileToCopy.copy(subFolder);
		
		// Move one of the folders to 'sub-folder'
		Folder folderToMove = firstFolder(topLevelFolder);
		println("> mv %1$s %2$s", folderToMove.getName(), subFolder.getName());
		folderToMove.move(subFolder);
		
		topLevelFolder = refresh(topLevelFolder);
		subFolder = refresh(subFolder);
		
		ls(topLevelFolder);
		ls(subFolder);
		
		// Delete the 'sub-folder'
		println("> rm %s", subFolder.getName());
		subFolder.delete();
		
		topLevelFolder = refresh(topLevelFolder);
		ls(topLevelFolder);
	}
	
	private void ls(Folder folder) {
		Collection children = getChildren(folder);
		
		println("> ls %1$s", folder.getName());
		
		println("total: %1$d", children.getTotal());
		for(Entity item : children.getMembers()) {
			if(item instanceof Folder) {
				Folder subFolder = (Folder) item;
				println("%1$-35s <DIR> %2$s", subFolder.getCreated(), subFolder.getName());
			} else if(item instanceof File) {
				File subFile = (File) item;
				println("%1$-35s       %2$s", subFile.getCreated(), subFile.getName());
			}
		}
	}
	
	private Folder refresh(Folder folder) {
		return folder.getSelf().get(Folder.class, true);
	}
	
	private File firstFile(Folder folder) {
		Collection children = getChildren(folder);
		
		for(Entity item : children.getMembers()) {
			if(item instanceof File)
				return (File) item;
		}
		return null;
	}
	
	private Folder firstFolder(Folder folder) {
		Collection children = getChildren(folder);
		
		for(Entity item : children.getMembers()) {
			if(item instanceof Folder)
				return (Folder) item;
		}
		return null;
	}
	
	private Collection getChildren(Folder folder) {
		Collection children = folder.findCollectionOrNull("children");
		
		if(children == null)
			children = folder.getAssociations().findLink("children").get(Collection.class);
		return children;
	}
	

}
