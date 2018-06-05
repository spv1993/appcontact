package edu.springproject.appcontact.exception;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionHandlerController {

//	@ExceptionHandler
//    ResponseEntity<ContactNotFoundException> ContactExceptionHandle(Exception ex) {
//		String msg = ex.getLocalizedMessage();
//		ContactNotFoundException body = new ContactNotFoundException(msg);
//        HttpStatus responseStatus = resolveAnnotatedResponseStatus(ex);
//        return new ResponseEntity<ContactNotFoundException>(body, responseStatus);
//    }
    
//	@ExceptionHandler
//    @ResponseBody
//    ResponseEntity<AccessTokenException> TokenExceptionHandle(Exception ex) {
//		String msg = ex.getLocalizedMessage();
//		AccessTokenException body = new AccessTokenException(msg);
//        HttpStatus responseStatus = resolveAnnotatedResponseStatus(ex);
//        return new ResponseEntity<AccessTokenException>(body, responseStatus);
//    }
	
    HttpStatus resolveAnnotatedResponseStatus(Exception ex) {
        ResponseStatus annotation = findMergedAnnotation(ex.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}