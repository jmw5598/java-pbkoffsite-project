package com.pbkoffsite.web.bean.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stockroom")
public class Stockroom extends SimpleIdDescription {
	
	
	//private int count;
	
	@OneToMany(mappedBy="stockroom", cascade={CascadeType.PERSIST})
	private List<Item> items;
	
	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		item.setStockroom(this);
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "Stockroom [id=" + id + ", description=" + description + "]";
	}
	
}
