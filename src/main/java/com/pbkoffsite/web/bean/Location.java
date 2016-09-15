package com.pbkoffsite.web.bean;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="location")
@JsonIgnoreProperties("items")
public class Location extends SimpleIdDescription{
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="location", cascade={CascadeType.PERSIST})
	private Collection<Item> items;
	
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		item.setLocation(this);
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", description=" + description + "]";
	}

	
		
	
	
}
