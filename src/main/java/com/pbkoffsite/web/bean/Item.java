package com.pbkoffsite.web.bean;

import java.util.Date;
import java.util.List;

public class Item {
	
	private int id;
	private Date dateAdded;
	private String addedBy;
	private int sku;
	private String description;
	private String stockroom;
	private String location;
	private String condition;
	private String additionalNotes;
	private List<String> imageUrls;
	
	public Item() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public String getAddedBy() {
		return addedBy;
	}
	
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStockroom() {
		return stockroom;
	}

	public void setStockroom(String stockroom) {
		this.stockroom = stockroom;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
	public List<String> getImageUrls() {
		return imageUrls;
	}
	
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	
}
