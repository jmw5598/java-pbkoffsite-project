package com.pbkoffsite.web.bean;

import java.util.Collection;
import java.util.Objects;

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
public class Sku extends SimpleIdDescription {
	
	@OneToMany(mappedBy="sku", cascade={CascadeType.PERSIST})
	
	private Collection<Item> items;
	
	
	public Sku() {}
	
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

	@Override
	public boolean equals(Object o) {
		
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		
		Sku sku = (Sku) o;
		
		return Objects.equals(id, sku.getId()) && 
				Objects.equals(description, sku.getDescription());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id,description);
	}
	
	

}
