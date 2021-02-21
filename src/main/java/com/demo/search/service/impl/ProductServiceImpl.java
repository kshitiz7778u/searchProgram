package com.demo.search.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.search.dto.Product;
import com.demo.search.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	//Map as inMemory storage for demo
	private Map<Integer, Product> productStorage = new HashMap<>();
		
	@Override
	public void save(Product product) {
		productStorage.put(product.getProductId(), product);
	}
	
	@Override
	public boolean productExist(Integer productId) {
		return productStorage.containsKey(productId);
	}

	@Override
	public Product get(Integer productId) {
		return productStorage.get(productId);
	}

	@Override
	public void update(Product product) {
		productStorage.put(product.getProductId(), product);
	}

	@Override
	public void delete(int productId) {
		productStorage.remove(productId);
	}

}
