package com.pbkoffsite.web.controller.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pbkoffsite.web.bean.entity.Item;
import com.pbkoffsite.web.bean.entity.SimpleIdDescription;
import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.bean.entity.BasicUserDetails;
import com.pbkoffsite.web.service.hibernate.ItemService;

@RestController
@RequestMapping(value="/api/items")
public class ItemRestController {
	
	@Autowired
	private ItemService itemService;
	
	
	
	
}
