package com.pbkoffsite.web.exception;

public class ItemNotFoundException extends Exception{
	
	public ItemNotFoundException() {
		super("ERROR: Unable to find item.");
	}
	
	public ItemNotFoundException(String message) {
		super(message);
	}
	
}
