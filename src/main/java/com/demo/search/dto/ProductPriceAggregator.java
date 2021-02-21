package com.demo.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPriceAggregator {
	Price price;
	Product product;
}
