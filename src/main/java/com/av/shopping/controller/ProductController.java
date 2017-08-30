package com.av.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.av.shopping.model.Product;
import com.av.shopping.service.ProductService;
/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Product controller for AV shopping api 
 * */
@RestController
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	@Qualifier("productService")
	ProductService productService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductList(){
		List<Product> listProduct=productService.getProductsDetails();
		return listProduct;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Product getProductDetails(@PathVariable int id){
		Product product=productService.getProductDetails(id);
		return product;
	}
}
