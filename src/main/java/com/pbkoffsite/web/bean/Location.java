package com.pbkoffsite.web.bean;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location extends SimpleIdDescription{
	
	@OneToMany(mappedBy="location", cascade={CascadeType.PERSIST})
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
