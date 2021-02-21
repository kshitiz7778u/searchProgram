package com.demo.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductPriceAggregatorException extends RuntimeException{

	private static final long serialVersionUID = -6843871731868346059L;

}
