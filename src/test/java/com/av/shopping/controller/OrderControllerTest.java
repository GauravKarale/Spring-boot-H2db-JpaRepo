package com.av.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.av.shopping.model.Order;
import com.av.shopping.model.Product;
import com.av.shopping.service.OrderService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=OrderController.class)
@WebMvcTest(value=OrderController.class, secure = false)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    @Qualifier(value="orderService")
    private  OrderService orderService;
    
    
    
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
		
		order= new Order(1,1,1,200.00,"CREDITCARD","USD",productList,"1,2");
		order1= new Order();
		orderList= new ArrayList<Order>();
		orderList.add(order);
	}
	
	@Test
	public void getOrderTest() throws Exception {
		Mockito.when(orderService.getOrderDetails(1)).thenReturn(order);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/order/"+1).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"orderID\":1,\"customerId\":1,\"cartId\":1,\"total\":200.0,\"paymentMode\":\"CREDITCARD\",\"currencyCode\":\"USD\",\"productList\":[{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.0,\"currencyCode\":\"USD\"},{\"productId\":2,\"name\":\"Worl war 2\",\"desc\":\"Call of duty\",\"category\":\"Electronics\",\"price\":230.0,\"currencyCode\":\"USD\"}],\"productQuantity\":\"1,2\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	@Test
	public void getOrdersTest() throws Exception {
		Mockito.when(orderService.getOrdersDetails()).thenReturn(orderList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/order/"+"all").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"orderID\":1,\"customerId\":1,\"cartId\":1,\"total\":200.0,\"paymentMode\":\"CREDITCARD\",\"currencyCode\":\"USD\",\"productList\":[{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.0,\"currencyCode\":\"USD\"},{\"productId\":2,\"name\":\"Worl war 2\",\"desc\":\"Call of duty\",\"category\":\"Electronics\",\"price\":230.0,\"currencyCode\":\"USD\"}],\"productQuantity\":\"1,2\"}]";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void putOrderTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				"/order/update")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"orderID\":1,\"customerId\":2,\"cartId\":1,\"total\":200.0,\"paymentMode\":\"CREDITCARD\",\"currencyCode\":\"USD\",\"productList\":[{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.0,\"currencyCode\":\"USD\"},{\"productId\":2,\"name\":\"Worl war 2\",\"desc\":\"Call of duty\",\"category\":\"Electronics\",\"price\":230.0,\"currencyCode\":\"USD\"}],\"productQuantity\":\"1,2\"}")
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
	}
	@Test
	public void postOrderTest() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/order/")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"orderID\":2,\"customerId\":1,\"cartId\":1,\"total\":200.0,\"paymentMode\":\"CREDITCARD\",\"currencyCode\":\"USD\",\"productList\":[{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.0,\"currencyCode\":\"USD\"},{\"productId\":2,\"name\":\"Worl war 2\",\"desc\":\"Call of duty\",\"category\":\"Electronics\",\"price\":230.0,\"currencyCode\":\"USD\"}],\"productQuantity\":\"1,2\"}")
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
	}
	
	@Test(expected = MethodArgumentTypeMismatchException.class)
	public void getOrderInvalidRequestTest() throws Exception,MethodArgumentTypeMismatchException {
		String var="XYZ";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/order/"+var).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
			throw 	result.getResolvedException();
	}
}
