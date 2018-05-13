package edu.springproject.appcontact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContactNotFoundException(long contactId) {
		super("could not find contact with '" + contactId + "'.");
	}
}