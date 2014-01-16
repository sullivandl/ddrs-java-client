package com.digidata.services.rest.client.examples;

import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.entities.Error;
import com.digidata.services.rest.client.entities.Folder;
import com.digidata.services.rest.client.entities.User;
import com.digidata.services.rest.client.exceptions.AlreadyExistsException;
import com.digidata.services.rest.client.exceptions.NotLoggedInException;

/**
 * This example illustrates the Ddrs' error handling mechanisms.  In the result of a 
 * server side error, the service returns an {@link Error} document.  The Ddrs will always
 * throw an exception in this case.  It also maps known {@link Error#getCode()}s to typed
 * exceptions.
 * 
 * @author dan.sullivan
 *
 */
public class ErrorHandlingExample extends BaseRestExample {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		
		// With a bad username and password, the Ddrs will throw a NotLoggedInException
		this.getDdrsFactory().switchUser("unknownuser", "badpassword");
		
		try {
			Ddrs.startFresh();
		} catch(NotLoggedInException ex) {
			println("Caught NotLoggedInExcpetion with error - %s", ex.getError());
		}
		
		this.getDdrsFactory().revertUser();
		
		// In the case of file system conflicts, the Ddrs will throw an AlreadyExistsException
		User me = Ddrs.startFresh();
		Folder folder = me.getRoot().mkdir(random());
		
		try {
			me.getRoot().mkdir(folder.getName());
		} catch(AlreadyExistsException ex) {
			println("Caught AlreadyExistsException with error - %s", ex.getError());
		}
	}

}
