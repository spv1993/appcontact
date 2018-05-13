package edu.springproject.appcontact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HttpStatusContactNotFoundException extends Exception {

	public HttpStatusContactNotFoundException(long contactId) {
		super("Could not find contact with '" + contactId + "'.");
	}
}