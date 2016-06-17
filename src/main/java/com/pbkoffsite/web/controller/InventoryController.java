package com.pbkoffsite.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.User;
import com.pbkoffsite.web.service.ItemService;


@Controller
@RequestMapping(value="/inventory")
public class InventoryController {
	
	@Autowired
	private ItemService itemService;
	
	//@Autowired
	//private UserDetailsService userService;
	
	@RequestMapping(value="/stockroom/{stockroom}", method=RequestMethod.GET)
	public String viewStockroomInventory(@PathVariable("stockroom") String stockroom, Model model) {
		
		model.addAttribute("stockroom", itemService.listItemsByStockroom(stockroom));
		model.addAttribute("stockrooms", itemService.countItemsByStockroom());
		model.addAttribute("selectedStockroom", itemService.countItemsByStockroom(stockroom));
		
		return "stockroom";
		
	}
	
	@RequestMapping(value="/stockroom/all", method=RequestMethod.GET)
	public String viewAllStockroomInventory(Model model) {
		
		model.addAttribute("stockroom", itemService.listAllItems());
		model.addAttribute("stockrooms", itemService.countItemsByStockroom());
		model.addAttribute("selectedStockroom", itemService.countItemsByStockroom("basement"));
		return "stockroom";
		
	}
	
	@RequestMapping(value="/item/{id}", method=RequestMethod.GET)
	public String viewItemByID(@PathVariable("id") int id, Model model) {
		
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		model.addAttribute("similarItems", itemService.listSimilarItems(item));
		model.addAttribute("stockrooms", itemService.countItemsByStockroom());
		model.addAttribute("removedReasons", itemService.listItemRemovedReasons());
		
		return "item";
		
	}
	
	@RequestMapping(value="/item/add", method=RequestMethod.GET)
	public String addItemForm() {
		
		return null;
		
	}
	
	@RequestMapping(value="/item/add", method=RequestMethod.POST)
	public String addItem(Item item) {
		
		return null;
		
	}
	
	@RequestMapping(value="/item/remove", method=RequestMethod.POST)
	public String removeItem(@RequestParam("item_id") Integer item_id,
							 @RequestParam("reason_id") Integer reason_id,
							 @RequestParam("stockroom") String stockroom, 
							 @AuthenticationPrincipal User user) {
		
		//User user = userService.findUserByUsername(principal.getName());
		int status = itemService.removeItem(item_id, reason_id, user.getId());
		
		return "redirect:/inventory/stockroom/" + stockroom;
		
	}
	
}
