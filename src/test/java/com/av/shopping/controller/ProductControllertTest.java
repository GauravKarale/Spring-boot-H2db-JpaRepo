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

import com.av.shopping.model.Product;
import com.av.shopping.service.ProductService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=ProductController.class)
@WebMvcTest(value=ProductController.class, secure = false)
public class ProductControllertTest {

	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    @Qualifier(value="productService")
    private  ProductService productService;
	
 

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
		Mockito.when(productService.getProductDetails(1)).thenReturn(product);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/product/"+1).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.00,\"currencyCode\":\"USD\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test(expected = MethodArgumentTypeMismatchException.class)
	public void getProductDetailsInvalidRequestTest() throws Exception,MethodArgumentTypeMismatchException {
		String var="XYZ";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/product/"+var).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
			throw 	result.getResolvedException();
	}
	
	@Test
	public void getAllProductDetailsTest() throws Exception {
		Mockito.when(productService.getProductsDetails()).thenReturn(productList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/product/"+"all").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"productId\":1,\"name\":\"GoPro HERO5 Black\",\"desc\":\"Capture different with HERO5 Black. Share immersive 4K perspectives that make you feel like you’re there.\",\"category\":\"Electronics\",\"price\":200.0,\"currencyCode\":\"USD\"},{\"productId\":2,\"name\":\"Worl war 2\",\"desc\":\"Call of duty\",\"category\":\"Electronics\",\"price\":230.0,\"currencyCode\":\"USD\"}]";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
