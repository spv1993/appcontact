package edu.springproject.appcontact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.springproject.appcontact.model.Contact;
import edu.springproject.appcontact.service.ContactService;

@RestController
public class ContactController {
    
	@Autowired
	ContactService service;
	
    @RequestMapping(
    		value = { "/contacts" }, 
    		method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Contact> contacts() {
        return service.getContacts();
    }
    
    @RequestMapping(
    		value = { "/contacts/{contactId}" }, 
    		method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
    public Contact contact(@PathVariable("contactId") long contactId) {
    	return service.getContact(contactId);
    }
    
    @RequestMapping(
    		value = { "/contacts" }, 
    		method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE })
    public Long createContact(@RequestBody Contact contact) {
    	return service.createContact(contact);
    }
    
    @RequestMapping(
    		value = { "/contacts/{contactId}" }, 
    		method = RequestMethod.PUT,
			produces = { MediaType.APPLICATION_JSON_VALUE })
    public Contact updateContact(@RequestBody Contact contact) {
    	service.updateContact(contact);
    	return null;
    }
    
    @RequestMapping(
    		value = { "/contacts/{contactId}" }, 
    		method = RequestMethod.DELETE,
			produces = { MediaType.APPLICATION_JSON_VALUE })
    public Contact deleteContact(@PathVariable("contactId") long contactId) {
    	service.removeContact(contactId);
    	return null;
    }
}
