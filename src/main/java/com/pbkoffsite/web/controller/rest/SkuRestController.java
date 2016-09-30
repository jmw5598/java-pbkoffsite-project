package com.pbkoffsite.web.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbkoffsite.web.bean.Sku;
import com.pbkoffsite.web.exception.SkuNotFoundException;
import com.pbkoffsite.web.service.SkuService;

@RestController
@RequestMapping("/api/sku")
public class SkuRestController {
	
	@Autowired
	private SkuService skuService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Sku> getSkus() {
		return skuService.list();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Sku getSku(@PathVariable("id") int id) throws SkuNotFoundException {
		
		Sku sku = skuService.find(id);
		
		if(sku == null)
			throw new SkuNotFoundException(id);
		
		return skuService.find(id);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleNotFound(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
