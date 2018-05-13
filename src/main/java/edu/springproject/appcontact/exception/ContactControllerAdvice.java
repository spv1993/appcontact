package edu.springproject.appcontact.exception;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ContactControllerAdvice {

	@ExceptionHandler
    @ResponseBody
    ResponseEntity<ContactNotFoundException> handle(Exception ex) {
		String msg = ex.getLocalizedMessage();
		ContactNotFoundException body = new ContactNotFoundException(msg);
        HttpStatus responseStatus = resolveAnnotatedResponseStatus(ex);
        return new ResponseEntity<ContactNotFoundException>(body, responseStatus);
    }
    
    HttpStatus resolveAnnotatedResponseStatus(Exception ex) {
        ResponseStatus annotation = findMergedAnnotation(ex.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}