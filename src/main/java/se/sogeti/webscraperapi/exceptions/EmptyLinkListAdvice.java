package se.sogeti.webscraperapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmptyLinkListAdvice {
    
    @ExceptionHandler(EmptyLinkListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String emptyLinkListAdvice(EmptyLinkListException e) {
        return e.getMessage();
    }    
}
