package com.av.shopping.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.av.shopping.model.Product;
import com.av.shopping.repository.ProductRepository;

/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Product service implementation for AV shopping api 
 * */
@Service(value="productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	private final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public Product getProductDetails(Integer id) {
		log.info("in  getProductDetails ");
		log.debug("id : "+id);
		Product prod=productRepo.findOne(id);
		if(prod==null){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product details for id ="+id+" not found");
		}
		return prod;
	}

	@Override
	public List<Product> getProductsDetails() {
		log.info("in  getProductsDetails ");
		List<Product> productList=productRepo.findAll();
		log.debug(" productList : "+productList);
		if(productList==null){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product details are not available");
		}
		return productList;
	}

}
