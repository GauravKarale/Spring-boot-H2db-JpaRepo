package com.av.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.av.shopping.model.Product;
import com.av.shopping.repository.ProductRepository;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ProductServiceImpl.class)
@SpringBootTest
@SpringBootConfiguration
public class ProductServiceImplTest {

	 @MockBean
	 private ProductRepository prodRepo;
	 
	 @Autowired
	 private ProductServiceImpl  service;
	 
	 Product product=null;
	 Product product1=null;
	 List<Product> productList=null;
	 
	 @Before
		public void setup() {
	 product=new Product();
			product.setProductId(1);
			product.setCurrencyCode("USD");
			product.setName("GoPro HERO5 Black");
			product.setDesc("Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.");
			product.setPrice(200.00);
			product.setCategory("Electronics");
			
			product1=new Product();
			product1.setProductId(2);
			product1.setCurrencyCode("USD");
			product1.setName("Worl war 2");
			product1.setDesc("Call of duty");
			product1.setPrice(230.00);
			product1.setCategory("Electronics");
			productList=new ArrayList<Product>();
			productList.add(product);
			productList.add(product1);
		}
	 
	 
		@Test
		public void getProductDetailsTest() throws Exception {
			Mockito.when(prodRepo.findOne(1)).thenReturn(product);
			Assert.assertEquals(product.toString(),service.getProductDetails(1).toString());
		}

		
		@Test(expected= HttpClientErrorException.class)
		public void getProductDetailsTestException() throws Exception {
			Mockito.when(prodRepo.findOne(1)).thenReturn(null);
			Assert.assertEquals(product.toString(),service.getProductDetails(1).toString());
		}
		
		
		@Test
		public void getProductsDetailsTest() throws Exception {
			Mockito.when(prodRepo.findAll()).thenReturn(productList);
			Assert.assertEquals(productList.toString(),service.getProductsDetails().toString());
		}
		
		@Test(expected= HttpClientErrorException.class)
		public void getProductsDetailsTestException() throws Exception {
			Mockito.when(prodRepo.findAll()).thenReturn(null);
			Assert.assertEquals(productList.toString(),service.getProductsDetails().toString());
		}
}
