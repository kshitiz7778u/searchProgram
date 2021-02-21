package com.demo.search.service;

import com.demo.search.dto.Product;

public interface ProductService {

	public void save(Product product);

	public Product get(Integer productId);

	public void update(Product product);

	public void delete(int productId);

	boolean productExist(Integer productId);

}
