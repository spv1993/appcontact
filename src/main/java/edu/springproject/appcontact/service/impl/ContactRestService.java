package edu.springproject.appcontact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.springproject.appcontact.dao.ContactDao;
import edu.springproject.appcontact.model.Contact;
import edu.springproject.appcontact.service.ContactService;

@Service
public class ContactRestService implements ContactService {

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
	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

	@Override
	public void removeContact(long id) {
		dao.removeContact(id);
	}

	@Override
	public Contact getContact(long id) {
		return dao.getContact(id);
	}

	@Override
	public boolean isContactExists(long id) {
		return dao.isContactExists(id);
	}
}
