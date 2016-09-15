package com.pbkoffsite.web.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.exception.StockroomNotFoundException;
import com.pbkoffsite.web.resource_assembler.StockroomResourceAssembler;
import com.pbkoffsite.web.service.StockroomService;

@RestController
@RequestMapping(value="/api/stockroom")
public class StockroomRestController {
	
	@Autowired
	private StockroomService stockroomService;
	
	@Autowired
	private StockroomResourceAssembler stockroomResourceAssebler;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Stockroom> getStockrooms() {
		return stockroomService.list();
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createStockroom() {
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<Stockroom> getStockroomById(@PathVariable("id") int id) throws StockroomNotFoundException {
		return null;
	}
	
	@RequestMapping(value="/{id}/items", method=RequestMethod.GET)
	public List<Item> getStockroomItems(@PathVariable("id") int id) {
		return stockroomService.listItems(id);
	}
	
	@ExceptionHandler(StockroomNotFoundException.class)
	public ResponseEntity<String> handleNotFound(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
