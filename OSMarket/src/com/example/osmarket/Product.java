package com.example.osmarket;

public class Product {
	int productCode;
	String productName;
	int productPrice;
	String productImage;
	
	@Override
	public String toString() {
		return productName;
	}
}
