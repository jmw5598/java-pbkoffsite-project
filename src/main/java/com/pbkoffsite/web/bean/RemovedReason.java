package com.pbkoffsite.web.bean;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="removed_reason")
@JsonIgnoreProperties({"items"})
public class RemovedReason extends SimpleIdDescription {
	
	@OneToMany(mappedBy="removedReason")
	private Collection<Item> items;
	
	
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		item.setRemovedReason(this);
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "RemovedReason [id=" + id + ", description=" + description + "]";
	}
	
	
	
}
