package com.example.demo.model;

import lombok.Data;

@Data
public class Product {
	String productId;
	String productName;
	String brand;
	String category;
	String modelName;
	int price;
	int stockQty;
	String description;
	String releseDate;
	
	//
	int total;
	
}
