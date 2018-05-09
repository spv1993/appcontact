package edu.springproject.appcontact.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ContactController {
    
    @RequestMapping(value = { "/contacts" }, method = RequestMethod.GET)
    public String contacts(Model model) {
        return "contacts";
    }
    
    @RequestMapping(value = { "/contacts/{id}" }, method = RequestMethod.GET)
    public String contact(@PathVariable("id") long id,Model model) {
        return "contact";
    }
}
