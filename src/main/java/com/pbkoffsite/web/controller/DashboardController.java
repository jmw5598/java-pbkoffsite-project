package com.pbkoffsite.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pbkoffsite.web.service.ItemService;
import com.pbkoffsite.web.service.StockroomService;

@Controller
public class DashboardController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private StockroomService stockroomService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("stockrooms", stockroomService.list());
		model.addAttribute("recentItems", itemService.listRecentlyAdded());
		model.addAttribute("floormodels", itemService.listFloormodel());
		
		return "home";
	
	}
	
}
