package edu.springproject.appcontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.springproject.appcontact.dao.ContactDao;
import edu.springproject.appcontact.model.Contact;

@Service
public class ContactDefaultService implements ContactService {
	
	@Autowired
	ContactDao dao;
	
	@Override
	public List<Contact> getContacts() {
		return dao.getContacts();
	}

	@Override
	public long createContact(Contact contact) {
		return dao.createContact(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		if(contact != null) {
			dao.updateContact(contact);
		}
		return contact;
	}

	@Override
	public Contact removeContact(long id) {
		Contact contact = dao.getContact(id);
		if(contact != null) {
			dao.removeContact(id);
		}
		return contact;
	}

	@Override
	public Contact getContact(long id) {
		return dao.getContact(id);
	}
}
