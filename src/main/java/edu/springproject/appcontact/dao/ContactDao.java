package edu.springproject.appcontact.dao;

import java.util.List;

import edu.springproject.appcontact.model.Contact;

public interface ContactDao {

	public long createContact(Contact contact);

	public void updateContact(Contact contact);

	public void removeContact(long id);

	public Contact getContact(long id);

	public List<Contact> getContacts();
	
	public boolean isContactExists(long id);
}
