package com.pbkoffsite.web.bean.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class BasicUserDetails extends AbstractUser {
	
	@OneToMany(mappedBy="addedBy")
	private List<Item> addedItems;
	
	@OneToMany(mappedBy="removedBy")
	private List<Item> removedItems;
	
}
