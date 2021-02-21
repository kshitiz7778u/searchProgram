package com.demo.search.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.search.dto.Price;
import com.demo.search.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService{
	
	private Map<Integer, Price> priceStorage = new HashMap<>();
	
	@Override
	public void save(Integer productId, Price price) {
		priceStorage.put(productId, price);
	}

	@Override
	public void update(Integer productId, Price price) {
		priceStorage.put(productId, price);
	}

	@Override
	public Price get(Integer productId) {
		return priceStorage.get(productId);
	}

	@Override
	public boolean isPriceAvailable(Integer productId) {
		return priceStorage.containsKey(productId);
	}

}
