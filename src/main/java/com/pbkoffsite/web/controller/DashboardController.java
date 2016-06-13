package com.pbkoffsite.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pbkoffsite.web.service.ItemService;

@Controller
public class DashboardController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("stockrooms", itemService.countItemsByStockroom());
		model.addAttribute("recentItems", itemService.listRecentlyAddedItems());
		model.addAttribute("floormodels", itemService.listFloormodelItems());
		
		return "home";
	
	}
	
}
