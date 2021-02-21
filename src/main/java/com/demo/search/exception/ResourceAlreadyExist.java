package com.demo.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExist extends RuntimeException{

	private static final long serialVersionUID = -4302535537392268126L;
	
	public ResourceAlreadyExist() {}

    public ResourceAlreadyExist(String message) {
        super(message);
    }

    public ResourceAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }
}
