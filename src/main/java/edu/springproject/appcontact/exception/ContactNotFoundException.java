package edu.springproject.appcontact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {

	private static final String ERROR_MESSAGE = "Could not find contact No. ";
	
    public ContactNotFoundException() {
    }

    public ContactNotFoundException(Long contactId) {
    	super(ERROR_MESSAGE + contactId);
    }
    
    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException(Throwable cause) {
        super(cause);
    }

    public ContactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}