package dev.ayush.productservice.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }
}


/* we add the response annotation so that if we dont want to use the controller advice
 and exception handler for the exception...then we can use this and to hide the stack trace from the user
 we can give a configuration in the application.properties.

 also this annotation automatically fills the JSON file on error when caused accordingly
 */