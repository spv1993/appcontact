package edu.springproject.appcontact.service;

import java.util.List;

import edu.springproject.appcontact.model.Contact;

public interface ContactService {

	public long createContact(Contact contact);

	public void updateContact(Contact contact);

	public void removeContact(long id);

	public Contact getContact(long id);

	public List<Contact> getContacts();
	
	public boolean contactIsExists(long id);
}
