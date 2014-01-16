package com.digidata.services.rest.client.examples;

import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.entities.User;

/**
 * This is the simplest of all examples.  Here we get the User's representation
 * from the server.
 * 
 * @author dan.sullivan
 *
 */
public class StartFreshExample extends BaseRestExample {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		
		// Get the user.
		User me = Ddrs.startFresh();
		println(me);
	}
}
