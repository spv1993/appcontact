package edu.springproject.appcontact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.springproject.appcontact.exception.ContactNotFoundException;
import edu.springproject.appcontact.model.Contact;
import edu.springproject.appcontact.service.ContactService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ContactRestController {

	@Autowired
	ContactService service;

	@GetMapping("/contacts")
	public List<Contact> contacts() {
		return service.getContacts();
	}

	@GetMapping("/contacts/{contactId}")
	public Contact contact(@PathVariable("contactId") long contactId)
			throws ContactNotFoundException {
		
		return service.getContact(contactId);
	}

	@PostMapping("/contacts")
	public Long createContact(@RequestBody Contact contact) {
		return service.createContact(contact);
	}

	@PutMapping("/contacts/{contactId}")
	public void updateContact(@RequestBody Contact contact)
			throws ContactNotFoundException {
		
		service.updateContact(contact);
	}

	@DeleteMapping("contacts/{contactId}")
	public void deleteContact(@PathVariable("contactId") long contactId)
			throws ContactNotFoundException {
		
		entryIsExists(contactId);
		service.removeContact(contactId);
	}

	private void entryIsExists(long contactId) throws ContactNotFoundException {
		if(!service.contactIsExists(contactId)) {
			throw new ContactNotFoundException(contactId);
		}
	}
}
