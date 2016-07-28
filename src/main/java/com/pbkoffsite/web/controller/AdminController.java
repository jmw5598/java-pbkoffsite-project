package com.pbkoffsite.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.bean.UserForm;
import com.pbkoffsite.web.service.ItemService;
import com.pbkoffsite.web.service.StockroomService;
import com.pbkoffsite.web.service.UserDetailsServiceImpl;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private StockroomService stockroomService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@RequestMapping(value="/cycleCount")
	public String cycleCount() {
		
		
		return "cycleCount";
		
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String users(Model model) {
		
		List<Role> roles = userDetailsService.listRoles();
		model.addAttribute("roles", roles);
		return "users";
		
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String addUser(@ModelAttribute UserForm user) {
		
		userDetailsService.create(user);
		return "redirect:/admin/users";
		
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String editUser() {
		
		return null;
		
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.POST)
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
