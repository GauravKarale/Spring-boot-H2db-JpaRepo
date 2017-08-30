package com.av.shopping.exception;
/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Custom message fur Exception handler for AV shopping api 
 * */
public class CustomErrorMessage{
	
	private String message;
	private String details;
	
	public CustomErrorMessage(String message,String details){
		this.message=message;
		this.details=details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "CustomErrorMessage [message=" + message + ", details=" + details + "]";
	}
	
	
}
