package com.av.shopping.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.av.shopping.model.Order;
import com.av.shopping.service.OrderService;

/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Order controller for AV shopping api 
 * */
@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final Logger log = Logger.getLogger(OrderController.class.getName());
	
	@Autowired
	@Qualifier("orderService")
	OrderService orderService;

	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public List<Order> getOrderList(){
		log.info("in  getOrderList ");
		List<Order> orderList=orderService.getOrdersDetails();
		return orderList;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Order getOrderDetails(@PathVariable int id){
		log.info("in  getOrderDetails ");
		log.debug("id: "+id);
		Order order=orderService.getOrderDetails(id);
		return order;
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public void addOrder(@RequestBody Order order) throws Exception{
		log.info("in  addOrder ");
		log.debug("Input Order"+order);
		orderService.addOrder(order);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT,consumes={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public void modifyOrderDetails(@RequestBody Order order) throws Exception{
		log.info("in  modifyOrderDetails ");
		log.debug("Input Order"+order);
		orderService.modifyOrder(order.getOrderID(),order);
	}
}
