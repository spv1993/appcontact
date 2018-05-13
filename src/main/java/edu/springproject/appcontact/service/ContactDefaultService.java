package edu.springproject.appcontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.springproject.appcontact.dao.ContactDao;
import edu.springproject.appcontact.exception.ContactNotFoundException;
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
	public void updateContact(Contact contact) throws ContactNotFoundException {
		dao.updateContact(contact);
	}

	@Override
	public void removeContact(long id) throws ContactNotFoundException {
		dao.removeContact(id);
	}

	@Override
	public Contact getContact(long id) throws ContactNotFoundException {
		return dao.getContact(id);
	}
}
