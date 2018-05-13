package edu.springproject.appcontact.dao;

import java.util.List;

import edu.springproject.appcontact.exception.ContactNotFoundException;
import edu.springproject.appcontact.model.Contact;

public interface ContactDao {

	public long createContact(Contact contact);

	public void updateContact(Contact contact) throws ContactNotFoundException;

	public void removeContact(long id) throws ContactNotFoundException;

	public Contact getContact(long id) throws ContactNotFoundException;

	public List<Contact> getContacts();
}
