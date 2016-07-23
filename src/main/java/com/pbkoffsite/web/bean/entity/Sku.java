package com.pbkoffsite.web.bean.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sku")
public class Sku {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="sku", cascade={CascadeType.PERSIST})
	
	private Collection<Item> items;
	
	
	public Sku() {}
	
	public Sku(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		item.setSku(this);
	}

	@Override
	public String toString() {
		return "Sku [id=" + id + ", description=" + description + "]";
	}

}
