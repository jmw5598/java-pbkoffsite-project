package com.pbkoffsite.web.bean;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="item_condition")
@JsonIgnoreProperties("items")
public class ItemCondition extends SimpleIdDescription {
	
	@OneToMany(mappedBy="itemCondition", cascade={CascadeType.PERSIST})
	private Collection<Item> items;
	
	
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		item.setItemCondition(this);
		this.items.add(item);
	}
	
	@Override
	public String toString() {
		return "ItemCondition [id=" + id + ", description=" + description + "]";
	}
	
	
	
}
