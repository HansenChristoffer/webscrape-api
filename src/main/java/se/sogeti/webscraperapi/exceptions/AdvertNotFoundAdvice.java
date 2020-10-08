package se.sogeti.webscraperapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvertNotFoundAdvice {
    
    @ExceptionHandler(AdvertNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String advertNotFoundAdvice(AdvertNotFoundException e) {
        return e.getMessage();
    }
}
