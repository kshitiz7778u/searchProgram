package com.demo.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.search.dto.Price;
import com.demo.search.exception.ResourceNotFoundException;
import com.demo.search.service.PriceService;
import com.demo.search.service.ProductService;

@RestController
@RequestMapping("/v1")
public class PriceController {

	@Autowired
	PriceService priceService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/price/{productId}", method= RequestMethod.POST)
    public ResponseEntity<Object> create(@PathVariable("productId") Integer productId, @RequestBody Price price){
		if (!productService.productExist(productId)){
            throw new ResourceNotFoundException();
        }
        priceService.save(productId, price);
        return new ResponseEntity<>("Product entry created successfully",HttpStatus.CREATED);
    }

    @RequestMapping(value="/price/{producId}", method= RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable("productId") Integer productId, @RequestBody Price price){
    	if (!productService.productExist(productId)){
            throw new ResourceNotFoundException();
        }
    	priceService.update(productId, price);
        return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
    }
    
    @RequestMapping(value="/price/{productId}")
    public ResponseEntity<Object> get(@PathVariable("productId") Integer productId){
    	if (!productService.productExist(productId)){
            throw new ResourceNotFoundException();
        }
    	if (!priceService.isPriceAvailable(productId)){
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(priceService.get(productId), HttpStatus.OK);
    }
}
