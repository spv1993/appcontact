package edu.springproject.appcontact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.springproject.appcontact.service.ContactService;

@Controller
public class ContactController {
    
	@Autowired
	ContactService service;
	
    @RequestMapping(value = { "/contacts" }, method = RequestMethod.GET)
    public String contacts(Model model) {
    	model.addAttribute("contacts", service.getContacts());
        return "contacts";
    }
    
    @RequestMapping(value = { "/contacts/{contactId}" }, method = RequestMethod.GET)
    public String contact(@PathVariable("contactId") long contactId,Model model) {
    	model.addAttribute("contact", service.getContact(contactId));
    	return "contact";
    }
}
