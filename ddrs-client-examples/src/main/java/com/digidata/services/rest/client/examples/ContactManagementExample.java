package com.digidata.services.rest.client.examples;

import com.digidata.services.rest.client.Ddrs;
import com.digidata.services.rest.client.Link;
import com.digidata.services.rest.client.entities.Contact;
import com.digidata.services.rest.client.entities.User;

/**
 * This example illustrates adding, modifying and retrieving a contact from the User's 
 * address book.
 * 
 * @author dan.sullivan
 *
 */
public class ContactManagementExample extends BaseRestExample {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		
		User me = Ddrs.startFresh();
		
		// Build some contact
		Contact newContact = new Contact();
		newContact.setEmailAddress(random() + "@ddrs-client.com");
		newContact.setFirstName("Miles");
		newContact.setLastName("Coltrane");
		
		// Add that contact
		Link contactLink = me.getAssociations().findLink("contacts");
		Contact addedContact = contactLink.post(newContact, Contact.class);
		println("Added Contact: %1$s", addedContact);
		
		// Update that contact
		addedContact.setFirstName("John");
		contactLink = addedContact.getSelf();
		contactLink.put(addedContact, Contact.class);

		// Retrieve the updated contact from the server
		Contact updatedContact = contactLink.get(Contact.class);
		println("Updated Contact: %1$s", updatedContact);
		
		// Delete the contact (if you feel so inclined..)
//		contactLink.delete();
//		println("Deleted Contact.");
	}

}
