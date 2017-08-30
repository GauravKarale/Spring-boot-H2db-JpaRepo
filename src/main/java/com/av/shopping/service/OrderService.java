package com.av.shopping.service;

import java.util.List;

import com.av.shopping.model.Order;
public interface OrderService {

	public Order getOrderDetails(Integer id);
	public List<Order> getOrdersDetails();
	public Order modifyOrder(Integer id,Order order) throws IllegalArgumentException;
	public void addOrder(Order order) throws IllegalArgumentException;
}
