package com.demo.search.dto;

import lombok.Data;

@Data
public class Product {
	
	private int productId;
	private String title;
	private int sellerId;
	private String manufacturer;
	private boolean isLowQuanity;
	private boolean isSoldOut;
	private boolean requiresShipping;
	private boolean isVisible;
}
