package edu.springproject.appcontact.service;

import java.util.List;

import edu.springproject.appcontact.model.Contact;

public interface ContactService {
	
	public long createContact(Contact contact);
	
	public Contact updateContact(Contact contact);
	
	public Contact removeContact(long id);
	
	public Contact getContact(long id);
	
	public List<Contact> getContacts();
}
