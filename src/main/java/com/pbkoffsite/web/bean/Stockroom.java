package com.pbkoffsite.web.bean;

import java.util.List;

import javax.persistence.CascadeType;		
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="stockroom")
@JsonIgnoreProperties("items")
public class Stockroom extends SimpleIdDescription {
	
	
	@Transient
	private long count;
	
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Stockroom [id=" + id + ", description=" + description + ", count="
				+ count + "]";
	}
	
	
	
}
