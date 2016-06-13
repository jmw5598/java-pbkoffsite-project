package com.pbkoffsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
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
}
