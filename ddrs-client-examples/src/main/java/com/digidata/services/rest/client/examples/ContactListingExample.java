package com.digidata.services.rest.client.examples;

import java.util.List;

import com.digidata.services.rest.client.Collection;
import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.entities.Contact;
import com.digidata.services.rest.client.entities.User;

/**
 * This example illustrates retrieving and listing the user's contacts.  Here we are also
 * showing the use of the 'compact' flag.  When compact=true a compact representation of
 * the entity is returned by the server.  Compact representations are smaller, but as seen here
 * requires additional round trips to the server to retrieve Collections .  In most cases you
 * will want to use compact=true.
 * 
 * @author dan.sullivan
 *
 */
public class ContactListingExample extends BaseRestExample {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		
		// Set to false to see the collection populated on the 'me' instance
		boolean startCompact = true;
	
		// Get your User.
		println("Starting fresh with compact=%1$s", startCompact);
		User me = Ddrs.startFresh(startCompact);
		
		// Find the 'contacts' collection associated with the user.
		Collection contactCollection = me.findCollectionOrNull("contacts");
		if(contactCollection == null) {
			
			// In this case; compcat=true, let's go get the collection via the Link.
			println("Contact collection not found on user. GETing 'contacts' resource");
			contactCollection = me.getAssociations().findLink("contacts").get(Collection.class);
		}
		
		// Display some details of our contacts Collection.
		println("Found %1$s-%2$s of total %3$s", contactCollection.getStart(), contactCollection.getCount(),
		                   contactCollection.getTotal());

		println("Contact Listing:");
		List<Contact> contacts = contactCollection.as(Contact.class).getMembersTyped();
		for(Contact c : contacts) {
			println("  " + c);
		}
	}

}
