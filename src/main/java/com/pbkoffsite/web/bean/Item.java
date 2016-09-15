package com.pbkoffsite.web.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="location_id")
	private Location location;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="sku_id")
	private Sku sku;
	
	@Column(name="date_added")
	private Date dateAdded;

	@ManyToOne
	@JoinColumn(name="added_by_id")
	private BasicUserDetails addedBy;
	
	@Column(name="date_removed")
	private Date dateRemoved;
	
	@ManyToOne
	@JoinColumn(name="removed_by_id")
	private BasicUserDetails removedBy;
	
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
	
	@ManyToMany(cascade={CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinTable(
		name="item_image",
		joinColumns={@JoinColumn(name="item_id")},
		inverseJoinColumns={@JoinColumn(name="image_id")}
	)
	private List<Image> images;
	
	//@ManyToMany(mappedBy="expectedItems")
	//private List<ShelfAudit> shelfAudits;
	
	
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

	public BasicUserDetails getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(BasicUserDetails addedBy) {
		this.addedBy = addedBy;
	}

	public Date getDateRemoved() {
		return dateRemoved;
	}

	public void setDateRemoved(Date dateRemoved) {
		this.dateRemoved = dateRemoved;
	}

	public BasicUserDetails getRemovedBy() {
		return removedBy;
	}

	public void setRemovedBy(BasicUserDetails removedBy) {
		this.removedBy = removedBy;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public void addImage(Image image) {
		this.images.add(image);
	}

//	public List<ShelfAudit> getShelfAudits() {
//		return shelfAudits;
//	}
//
//	public void setShelfAudits(List<ShelfAudit> shelfAudits) {
//		this.shelfAudits = shelfAudits;
//	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", location=" + location + ", sku=" + sku
				+ ", dateAdded=" + dateAdded + ", addedBy=" + addedBy
				+ ", dateRemoved=" + dateRemoved + ", removedBy=" + removedBy
				+ ", removedReason=" + removedReason + ", additionNotes="
				+ additionNotes + ", isAvailable=" + isAvailable
				+ ", stockroom=" + stockroom + ", itemCondition="
				+ itemCondition + ", images=" + images + "]";
	}
	
			
	
}
