package com.av.shopping.service;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.av.shopping.model.Order;
import com.av.shopping.model.Product;
import com.av.shopping.repository.OrderRepository;
import com.av.shopping.repository.ProductRepository;
/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Order service implementation for AV shopping api 
 * */

@Service(value="orderService")
@Transactional
public class OrderServiceImpl implements OrderService{

	private final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductRepository productRepo;

	private enum paymentMode{
		DEBITCARD,CREDITCARD,ONLINE_BANKING;
	}
	
	@Override
	@Cacheable(value = "orderCache", key = "#id")
	public Order getOrderDetails(Integer id) {
		log.info("in  getOrderDetails ");
		log.debug("id: "+id);
		Order order=orderRepo.findOne(id);
		if(order==null){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Order details for id="+id+" Not found");
		}
		return order;
	}

	@Override
	public List<Order> getOrdersDetails() {
		log.info("in  getOrdersDetails ");
		List<Order> orderList=orderRepo.findAll();
		log.debug("order list"+orderList);
		if(orderList==null){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Order details are not available");
		}
		return orderList;
	}

	@Override
	@CachePut(value = "orderCache", key = "#id")
	public Order modifyOrder(Integer id,Order order) throws IllegalArgumentException {
		log.info("in  modifyOrder ");
		log.debug("id: "+id);
		Order updatedOrder=setTotalPrice(order);
		checkProductCatalog(updatedOrder);
		checkForValiPaymentMode(updatedOrder);
		updatedOrder.setOrderDate(new Date());
		orderRepo.saveAndFlush(updatedOrder);
		return updatedOrder;
	}

	@Override
	public void addOrder(Order order) throws IllegalArgumentException {
		log.info("in  addOrder ");
		log.debug("id: "+order.getOrderID());
		Order updatedOrder=setTotalPrice(order);
		checkProductCatalog(updatedOrder);
		checkForValiPaymentMode(updatedOrder);
		updatedOrder.setOrderDate(new Date());
		orderRepo.saveAndFlush(updatedOrder);
	}
	
	private boolean checkProductCatalog(Order order) throws IllegalArgumentException{
		log.info("in  checkProductCatalog ");
		
		Collection<Product>productList=order.getProductList();
		for (Product product : productList) {
			Product product1=productRepo.findOne(product.getProductId());
				if(product1==null){
					throw new IllegalArgumentException("Product with id="+product.getProductId()+" is not present in catlog");
				}
				if(!product.equals(product1)){
					throw new IllegalArgumentException("Product details with id="+product.getProductId()+" is modified in order, product details cannot be modified");
				}
				checkCurrencyCode(product.getCurrencyCode(),order.getOrderID());
		}
		return true;
	}
	
	private void checkForValiPaymentMode(Order order){
		log.info("in  checkForValiPaymentMode ");
		paymentMode mode;
		try {
		       mode = paymentMode.valueOf(order.getPaymentMode());
		    } catch (IllegalArgumentException ex) {  
		    	throw new IllegalArgumentException("Payment entered is not valid");
		  }
	}
	
	private Order setTotalPrice(Order order){
		log.info("in  setTotalPrice ");
		String[] quantity=order.getProductQuantity().split(",");
		int i=0;
		double totalPrice=0;
		
		Collection<Product>productList=order.getProductList();
		if(quantity.length!=productList.size()){
			throw new IllegalArgumentException("Please enter quantity respective to product");
		}
		
		for (Product product : productList) {
			if(i==quantity.length)
				break;
			double noItem=Double.valueOf(quantity[i]);
			if(noItem>0)
				totalPrice+=product.getPrice()*noItem;
			i+=1;
		}
		order.setTotal(totalPrice);
		return order;
	}
	
	private void checkCurrencyCode(String currencyCode,Integer id){
		
		try
		{
			final Currency currency = Currency.getInstance(currencyCode);
		}
		catch (final IllegalArgumentException x)
		{
			throw new IllegalArgumentException(x.getMessage()+"Check currency code for order id: "+id);
		}
	}
}
