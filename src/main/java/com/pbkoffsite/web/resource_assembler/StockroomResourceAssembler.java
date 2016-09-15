package com.pbkoffsite.web.resource_assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.controller.rest.StockroomRestController;

@Component
public final class StockroomResourceAssembler implements ResourceAssembler<Stockroom, Resource<Stockroom>> {

	@Override
	public Resource<Stockroom> toResource(Stockroom stockroom) {
		
		Resource<Stockroom> resource = new Resource<Stockroom>(stockroom);
		resource.add(linkTo(StockroomRestController.class).slash(stockroom.getId()).slash("items").withRel("items"));
		resource.add(linkTo(StockroomRestController.class).slash(stockroom.getId()).withSelfRel());
		return resource;
	}
	
	
	
}
