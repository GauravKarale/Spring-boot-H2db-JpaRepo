package com.av.shopping.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Order entity class mapped with H2 db table orders 
 * */
@Entity
@Table(name="orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2564198989340010307L;

	@Id
	@Column(name="id")
	private Integer orderID;
	
	@Column(name="customer_id")
	private Integer customerId;
	@Column(name="cart_id")
	private Integer cartId;
	@Column(name="total_price")
	private Double total;
	@Column(name="payment_mode")
	private String paymentMode;
	@Column(name="currency_code")
	private String currencyCode;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST,
			CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable(name="order_product",joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="product_id"))
	private Collection<Product> productList=new ArrayList<Product>();
	
	@Column(name="product_quantity")
	private String productQuantity;
	
	@Column(name="order_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	public Order(){
		
	}
	
	
	
	public Order(Integer orderID, Integer customerId, Integer cartId, Double total, String paymentMode,
			String currencyCode, Collection<Product> productList, String productQuantity) {
		super();
		this.orderID = orderID;
		this.customerId = customerId;
		this.cartId = cartId;
		this.total = total;
		this.paymentMode = paymentMode;
		this.currencyCode = currencyCode;
		this.productList = productList;
		this.productQuantity = productQuantity;
	}



	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Collection<Product> getProductList() {
		return productList;
	}
	public void setProductList(Collection<Product> productList) {
		this.productList = productList;
	}
	public String getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerId=" + customerId + ", cartId=" + cartId + ", total=" + total
				+ ", paymentMode=" + paymentMode + ", currencyCode=" + currencyCode + ", productList=" + productList
				+ ", productQuantity=" + productQuantity + "]";
	}
	
}
