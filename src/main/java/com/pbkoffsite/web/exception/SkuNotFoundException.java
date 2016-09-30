package com.pbkoffsite.web.exception;

public class SkuNotFoundException extends Exception {
	
	public SkuNotFoundException() {
		super("ERROR: Sku not found");
	}
	
	public SkuNotFoundException(int id) {
		super("ERROR: Sku not found with id " + id);
	}

}
