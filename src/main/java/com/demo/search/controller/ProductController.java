package com.demo.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.search.dto.Product;
import com.demo.search.exception.ResourceNotFoundException;
import com.demo.search.service.ProductService;

@RestController
@RequestMapping("/v1")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;

    @RequestMapping(value="/product", method= RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
    	logger.info("Adding product {} to the storage", product.getProductId());
        productService.save(product);
        logger.info("Product {} added successfully", product.getProductId());
        return new ResponseEntity<>("Product entry created successfully",HttpStatus.CREATED);
    }

    @RequestMapping(value="/product/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Integer productId){
    	logger.info("Get product {} ", productId);
		if (!productService.productExist(productId)){
			logger.info("Product does not exist");
            throw new ResourceNotFoundException("Product does not exist");
        }
		logger.info("Successfully returning {} to client", productId);
        return new ResponseEntity<>(productService.get(productId), HttpStatus.OK);
    }

    @RequestMapping(value="/product", method= RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
    	logger.info("update product {} ", product.getProductId());
    	productService.update(product);
    	logger.info("product updated successfully");
        return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
    }

    @RequestMapping(value="/product/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer productId){
    	productService.delete(productId);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }
	
}
