package com.demo.search.service;

import com.demo.search.dto.Price;

public interface PriceService {

	public void save(Integer id, Price price);

	public Price get(Integer producId);

	public void update(Integer id, Price price);

	public boolean isPriceAvailable(Integer productId);

}
