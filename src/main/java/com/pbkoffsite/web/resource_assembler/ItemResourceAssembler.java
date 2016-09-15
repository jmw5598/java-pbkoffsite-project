package com.pbkoffsite.web.resource_assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.controller.rest.ItemRestController;

@Component
public class ItemResourceAssembler implements ResourceAssembler<Item, Resource<Item>> {

	@Override
	public Resource<Item> toResource(Item item) {
		
		Resource<Item> resource = new Resource<>(item);
		resource.add(linkTo(ItemRestController.class).slash(item.getId()).withSelfRel());
		resource.add(linkTo(ItemRestController.class).slash(item.getId()).slash("location").withRel("location"));
		resource.add(linkTo(ItemRestController.class).slash(item.getId()).slash("stockroom").withRel("stockroom"));
		resource.add(linkTo(ItemRestController.class).slash(item.getId()).slash("condition").withRel("condition"));
		resource.add(linkTo(ItemRestController.class).slash(item.getId()).slash("availibility").withRel("availability"));
		
		return resource;
	}

}
