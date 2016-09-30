package com.pbkoffsite.web.resource_assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.pbkoffsite.web.bean.Sku;
import com.pbkoffsite.web.controller.rest.SkuRestController;

@Component
public class SkuResourceAssembler implements ResourceAssembler<Sku, Resource<Sku>>{

	@Override
	public Resource<Sku> toResource(Sku sku) {
		Resource<Sku> resource = new Resource<>(sku);
		resource.add(linkTo(SkuRestController.class).slash(sku.getId()).withSelfRel());
		return resource;
	}

}
