package com.av.shopping.service;

import java.util.List;

import com.av.shopping.model.Product;

public interface ProductService {

	public Product getProductDetails(Integer id);
	public List<Product> getProductsDetails();
}
