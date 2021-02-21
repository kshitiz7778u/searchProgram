package com.demo.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.search.error.ErrorHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorHandler> handleProductNotFoundException(ResourceNotFoundException ex) {
        ErrorHandler error = new ErrorHandler(HttpStatus.NO_CONTENT.toString(), ex.getMessage(), HttpStatus.NO_CONTENT.toString());
        return new ResponseEntity<ErrorHandler>(error, HttpStatus.NO_CONTENT);
    }
    
    @ExceptionHandler(ProductPriceAggregatorException.class)
    public final ResponseEntity<ErrorHandler> handleProductPriceAggregatorException(ProductPriceAggregatorException ex) {
        ErrorHandler error = new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error while aggregating price and product info", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<ErrorHandler>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResourceAlreadyExist.class)
    public final ResponseEntity<ErrorHandler> handleResourceAlreadyExist(ResourceAlreadyExist ex) {
        ErrorHandler error = new ErrorHandler(HttpStatus.CONFLICT.toString(), ex.getMessage(), HttpStatus.CONFLICT.toString());
        return new ResponseEntity<ErrorHandler>(error, HttpStatus.CONFLICT);
    }

}
