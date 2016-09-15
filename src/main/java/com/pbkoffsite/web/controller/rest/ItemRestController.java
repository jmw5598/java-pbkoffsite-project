package com.pbkoffsite.web.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.ItemCondition;
import com.pbkoffsite.web.bean.Location;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.exception.ItemNotFoundException;
import com.pbkoffsite.web.resource_assembler.ItemResourceAssembler;
import com.pbkoffsite.web.service.ItemService;

@RestController
@RequestMapping(value="/api/item")
public class ItemRestController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemResourceAssembler itemResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Item> getItems() {
		return itemService.list();
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Item create() {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Item delete() {
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<Item> getItem(@PathVariable("id") int id) throws ItemNotFoundException {
		Item item = itemService.findById(id);
		if(item == null) throw new ItemNotFoundException();
		return itemResourceAssembler.toResource(item);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Item updateItem(@PathVariable("id") int id) throws ItemNotFoundException {
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Item removeItem(@PathVariable("id") int id) throws ItemNotFoundException {
		return null;
	}
	
	@RequestMapping(value="/{id}/location", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Location getItemLocation(@PathVariable("id") int id) throws ItemNotFoundException {
		Location location = itemService.getItemLocation(id);
		if(location == null) throw new ItemNotFoundException();
		return location;
	}
	
	@RequestMapping(value="/{id}/stockroom", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Stockroom getItemStockroom(@PathVariable("id") int id) throws ItemNotFoundException {
		Stockroom stockroom = itemService.getItemStockroom(id);
		if(stockroom == null) throw new ItemNotFoundException();
		return stockroom;
	}
	
	@RequestMapping(value="/{id}/condition", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ItemCondition getItemCondition(@PathVariable("id") int id) throws ItemNotFoundException {
		ItemCondition itemCondition = itemService.getItemCondition(id);
		if(itemCondition == null) throw new ItemNotFoundException();
		return itemCondition;
	}
	
	@RequestMapping(value="/{id}/availability", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean getItemAvailibility(@PathVariable("id") int id) throws ItemNotFoundException {
		
		Boolean isAvailable = itemService.isAvailable(id);
		if(isAvailable == null) throw new ItemNotFoundException();
		
		return isAvailable;
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<String> handleNotFound(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
