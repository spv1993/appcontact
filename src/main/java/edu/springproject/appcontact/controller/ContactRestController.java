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
    public Contact contact(@PathVariable("contactId") long contactId) {
    	return service.getContact(contactId);
    }
    
    @PostMapping("/contacts")
    public Long createContact(@RequestBody Contact contact) {
    	return service.createContact(contact);
    }
    
    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(@RequestBody Contact contact) {
    	return service.updateContact(contact);
    }
    
    @DeleteMapping("contacts/{contactId}")
    public Contact deleteContact(@PathVariable("contactId") long contactId) {
    	return service.removeContact(contactId);
    }
}
