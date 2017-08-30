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

import com.av.shopping.model.Order;
import com.av.shopping.model.Product;
import com.av.shopping.repository.OrderRepository;
import com.av.shopping.repository.ProductRepository;
import com.av.shopping.service.OrderServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=OrderServiceImpl.class)
@SpringBootTest
@SpringBootConfiguration
public class OrderServiceImplTest {

	 @MockBean
	 private  OrderRepository orderRepo;
	 
	 @MockBean
	 private ProductRepository prodRepo;
	 
	 @Autowired
	 private OrderServiceImpl  service;
	 
	 Product product=null;
		Product product1=null;
		List<Product> productList=null;
		List<Order> orderList=null;
		
		Order order=null;
		Order order1=null;
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
			
			order= new Order(1,1,1,660.00,"CREDITCARD","USD",productList,"1,2");
			order1= new Order();
			orderList= new ArrayList<Order>();
			orderList.add(order);
		}
		
		
		@Test
		public void getOrderDetailsTest() throws Exception {
			Mockito.when(orderRepo.findOne(1)).thenReturn(order);
			Assert.assertEquals(order.toString(),service.getOrderDetails(1).toString());
		}

		
		@Test(expected= HttpClientErrorException.class)
		public void getOrderDetailsTestException() throws Exception {
			Mockito.when(orderRepo.findOne(1)).thenReturn(null);
			Assert.assertEquals(order.toString(),service.getOrderDetails(1).toString());
		}
		
		@Test(expected= IllegalArgumentException.class)
		public void addOrderDetailsTestInvalidPaymentdetails() throws Exception {
			order.setPaymentMode("");
			service.addOrder(order);
		}
		
		@Test
		public void getOrdersDetailsTest() throws Exception {
			Mockito.when(orderRepo.findAll()).thenReturn(orderList);
			Assert.assertEquals(orderList.toString(),service.getOrdersDetails().toString());
		}
		
		@Test(expected= HttpClientErrorException.class)
		public void getOrdersDetailsTestException() throws Exception {
			Mockito.when(orderRepo.findAll()).thenReturn(null);
			Assert.assertEquals(orderList.toString(),service.getOrdersDetails().toString());
		}
		
		@Test
		public void putOrdersDetailsTest() throws Exception {
			order.setPaymentMode("DEBITCARD");
			Mockito.when(prodRepo.findOne(product.getProductId())).thenReturn(product);
			Mockito.when(prodRepo.findOne(product1.getProductId())).thenReturn(product1);
		
			Mockito.when(orderRepo.saveAndFlush(order)).thenReturn(null);
			Assert.assertEquals(order.toString(),service.modifyOrder(order.getOrderID(), order).toString());
		}
}
