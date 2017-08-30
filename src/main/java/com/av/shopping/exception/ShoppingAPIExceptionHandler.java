package com.av.shopping.exception;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Exception handler for AV shopping api 
 * */
@Component
@ControllerAdvice
public class ShoppingAPIExceptionHandler extends ResponseEntityExceptionHandler{
	
	private final Logger log = Logger.getLogger(ShoppingAPIExceptionHandler.class.getName());
	
	
	@ExceptionHandler(IOException.class)
	public final ResponseEntity<CustomErrorMessage> ioExceptionHandler(IOException ex){
		log.info("in ioExceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage()," JSON parsing failed while retreiving requested Id");
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<CustomErrorMessage> httpClientErrorExceptionHandler(HttpClientErrorException ex){
		log.info("in httpClientErrorExceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage(),"Details are not available for requested id");
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<CustomErrorMessage> methodArgTypeMiExceptionHandler(MethodArgumentTypeMismatchException ex){
		log.info("in httpClientErrorExceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage()," Failed to convert value of argument, expected number but got something else  ");
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	
	/*@ExceptionHandler(MongoException.class)
	public final ResponseEntity<CustomErrorMessage> mongoExceptionHandler(MongoException ex){
		log.info("in mongoExceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage()," Product details are not available in mongo dbcollection 'productprice' ");
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}*/
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomErrorMessage> exceptionHandler(Exception ex){
		log.info("in exceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage(),ex.getMessage());
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<CustomErrorMessage> illegalArgumentExceptionHandler(IllegalArgumentException ex){
		log.info("in illegalArgumentExceptionHandler");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage(),ex.getMessage());
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(JsonMappingException.class)
	public final ResponseEntity<CustomErrorMessage> illegalArgumentExceptionHandler(JsonMappingException ex){
		log.info("in JsonMappingException");
		CustomErrorMessage exceptionResponse= new CustomErrorMessage(ex.getMessage(),ex.getMessage());
		log.debug("customErrorMessage : "+exceptionResponse);
		return new ResponseEntity<CustomErrorMessage>(exceptionResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
}
