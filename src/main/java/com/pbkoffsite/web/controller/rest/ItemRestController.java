package com.pbkoffsite.web.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.SimpleIdDescription;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.bean.User;
import com.pbkoffsite.web.service.ItemService;

@RestController
@RequestMapping(value="/api/items")
public class ItemRestController {
	
	@Autowired
	private ItemService itemService;
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public Item itemById(@PathVariable("id") int id) {
		
		return itemService.findItemById(id);
		
	}
	
	
	@RequestMapping(value="/stockroom/{stockroom}", method=RequestMethod.GET, produces="application/json")
	public List<Item> itemsByStockroom(@PathVariable("stockroom") String stockroom) {
		
		return itemService.listItemsByStockroom(stockroom);
		
	}
	
	@RequestMapping(value="/recent", method=RequestMethod.GET, produces="application/json")
	public List<Item> itemsRecentlyAdded() {
		
		return itemService.listRecentlyAddedItems();
		
	}
	
	@RequestMapping(value="/principal", method=RequestMethod.GET, produces="application/json")
	public Principal itemsRecentlyAdded(Principal principal) {
		
		return principal;
		
	}
	
	@RequestMapping(value="/floormodel", method=RequestMethod.GET, produces="application/json")
	public List<Item> floormodelItems() {
		
		return itemService.listFloormodelItems();
		
	}
	
	@RequestMapping(value="/stockroom/count", method=RequestMethod.GET, produces="application/json")
	public List<Stockroom> itemCountsPerStockroom() {
		
		return itemService.countItemsByStockroom();
		
	}
	
	@RequestMapping(value="/stockroom/count/{stockroom}", method=RequestMethod.GET, produces="application/json")
	public Stockroom itemCountByStockroom(@PathVariable("stockroom") String stockroom ) {
		
		return itemService.countItemsByStockroom(stockroom);
		
	}
	
	
	
	
	@RequestMapping(value="/remove/reasons", method=RequestMethod.GET, produces="application/json")
	public List<SimpleIdDescription> listRemovedReasons() {
		
		return itemService.listItemRemovedReasons();
		
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, produces="application/json")
	public List<Item> listAllItems() {
		
		return itemService.listAllItems();
		
	}
}
