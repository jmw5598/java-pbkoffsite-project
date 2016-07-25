package com.pbkoffsite.web.bean.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="location_id")
	private Location location;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="sku_id")
	private Sku sku;
	
	@Column(name="date_added")
	private Date dateAdded;

	// link to added by user : Use BasicUserDetails class
	
	@Column(name="date_removed")
	private Date dateRemoved;
	
	// link to removed by user : Use BasicUserDetails class
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="removed_reason_id")
	private RemovedReason removedReason;
	
	@Column(name="additional_notes")
	private String additionNotes;
	
	@Column(name="is_available")
	private boolean isAvailable;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="stockroom_id")
	private Stockroom stockroom;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="item_condition_id")
	private ItemCondition itemCondition;
	
	public Item() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateRemoved() {
		return dateRemoved;
	}

	public void setDateRemoved(Date dateRemoved) {
		this.dateRemoved = dateRemoved;
	}

	public RemovedReason getRemovedReason() {
		return removedReason;
	}

	public void setRemovedReason(RemovedReason removedReason) {
		this.removedReason = removedReason;
	}

	public String getAdditionNotes() {
		return additionNotes;
	}

	public void setAdditionNotes(String additionNotes) {
		this.additionNotes = additionNotes;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Stockroom getStockroom() {
		return stockroom;
	}

	public void setStockroom(Stockroom stockroom) {
		this.stockroom = stockroom;
	}

	public ItemCondition getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(ItemCondition itemCondition) {
		this.itemCondition = itemCondition;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", location=" + location + ", sku=" + sku
				+ ", dateAdded=" + dateAdded + ", dateRemoved=" + dateRemoved
				+ ", removedReason=" + removedReason + ", additionNotes="
				+ additionNotes + ", isAvailable=" + isAvailable
				+ ", stockroom=" + stockroom + ", itemCondition="
				+ itemCondition + "]";
	}

		
	
}
