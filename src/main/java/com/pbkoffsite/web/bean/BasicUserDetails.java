package com.pbkoffsite.web.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
@JsonIgnoreProperties({"addedBy","removedBy"})
public class BasicUserDetails extends AbstractUser {
	
	@OneToMany(mappedBy="addedBy")
	private List<Item> addedItems;
	
	@OneToMany(mappedBy="removedBy")
	private List<Item> removedItems;
	
}
