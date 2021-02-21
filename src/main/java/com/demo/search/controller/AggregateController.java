package com.demo.search.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.search.dto.Price;
import com.demo.search.dto.Product;
import com.demo.search.dto.ProductPriceAggregator;
import com.demo.search.exception.ProductPriceAggregatorException;
import com.demo.search.exception.ResourceNotFoundException;
import com.demo.search.service.PriceService;
import com.demo.search.service.ProductService;

@RestController
@RequestMapping("/v1")
public class AggregateController {
	
	@Autowired
	ProductService productService;
	@Autowired
	PriceService priceService;
	
	@RequestMapping(value="/aggregate/{productId}")
    public ResponseEntity<Object> get(@PathVariable("productId") Integer productId){
		
    	if (!productService.productExist(productId)){
            throw new ResourceNotFoundException();
        }
    	CompletableFuture<Product> product = CompletableFuture.supplyAsync(() -> productService.get(productId));
    	CompletableFuture<Price> price = CompletableFuture.supplyAsync(() -> priceService.get(productId));
        ProductPriceAggregator priceAggregator = null;
		try {
			priceAggregator = new ProductPriceAggregator(price.get(),product.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new ProductPriceAggregatorException();
		}
        return new ResponseEntity<>(priceAggregator, HttpStatus.OK);
    }

}
