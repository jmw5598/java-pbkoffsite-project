package com.pbkoffsite.web.exception;

public class StockroomNotFoundException extends Exception {
	
	public StockroomNotFoundException() {
		super("ERROR: Unable to find stockroom");
	}
	
	public StockroomNotFoundException(String message) {
		super(message);
	}
	
}
