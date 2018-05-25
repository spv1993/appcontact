package edu.springproject.appcontact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;


@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessTokenException extends AuthenticationException{

	private static final String ERROR_MESSAGE = "JWT String argument cannot be null or empty.";
	
    public AccessTokenException() {
    	super(ERROR_MESSAGE);
    }
    
    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}