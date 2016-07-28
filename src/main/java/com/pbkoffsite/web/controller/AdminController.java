package com.pbkoffsite.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.service.ItemService;
import com.pbkoffsite.web.service.StockroomService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private StockroomService stockroomService;
	
	@RequestMapping(value="/cycleCount")
	public String cycleCount() {
		
		
		return "cycleCount";
		
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String users() {
		
		return "userAccounts";
		
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String addUser() {
		
		return null;
		
	}
	
	@RequestMapping(value="/users/edit/{id}", method=RequestMethod.GET)
	public String editUser() {
		
		return null;
		
	}
	
	@RequestMapping(value="/users/update", method=RequestMethod.PUT)
	public String updateUser() {
		
		return null;
		
	}
	
	@RequestMapping(value="/activity")
	public String activity() {
		
		return "activity";
		
	}
	
	@RequestMapping(value="/item/remove", method=RequestMethod.GET)
	public String removedItems(Model model) {
		
		List<Item> items = itemService.listRemoved();
		List<Stockroom> stockrooms = stockroomService.list();
		model.addAttribute("removedItems", items);
		model.addAttribute("stockrooms", stockrooms);
		
		
		return "removed";
		
	}
	
	@RequestMapping(value="/item/remove/undo/{id}", method=RequestMethod.GET)
	public String undoRemovedItem(@PathVariable("id") int itemId) {
		itemService.undoRemove(itemId);
		return "redirect:/admin/item/remove";
	}
}
