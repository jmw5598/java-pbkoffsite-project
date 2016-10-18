package com.pbkoffsite.web.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.BasicUserDetails;
import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.ItemCondition;
import com.pbkoffsite.web.bean.Location;
import com.pbkoffsite.web.bean.RemovedReason;
import com.pbkoffsite.web.bean.Stockroom;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ItemRepositoryImpl implements ItemRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Item> list() {
		return em.createQuery("from Item").getResultList();
	}
	
	@Override
	public List<Item> listAvailable() {
		return em.createQuery("FROM Item AS item WHERE item.isAvailable = true")
				.getResultList();
	}

	@Override
	public List<Item> listByStockroomId(int id) {
		return em.createQuery("FROM Item AS item WHERE item.stockroom.id = :id AND item.isAvailable = true")
				.setParameter("id", id)
				.getResultList();
	}

	
	@Override
	public List<Item> listRecentlyAdded() {
		return em.createQuery("FROM Item AS item WHERE item.isAvailable = true ORDER BY item.dateAdded DESC")
				.setFirstResult(0)
				.setMaxResults(15)
				.getResultList();
	}

	@Override
	public List<Item> listSimilar(Item item) {
		
		return em.createQuery("FROM Item AS item WHERE item.sku.id = :skuId AND item.id != :itemId AND item.isAvailable = true")
				.setParameter("skuId", item.getSku().getId())
				.setParameter("itemId", item.getId())
				.getResultList();
		
	}

	@Override
	public List<Item> listFloormodel() {
		return em.createQuery("FROM Item AS item WHERE item.itemCondition.id != 1 AND item.itemCondition.id != 2 AND item.isAvailable = true")
				.getResultList();
	}
	
	@Override
	public List<Item> listRemoved() {
		return em.createQuery("FROM Item AS item WHERE isAvailable = false ORDER BY item.dateRemoved DESC")
				.getResultList();
	}

	@Override
	public Item findById(int id) {
		return em.find(Item.class, id);
	}

	@Override
	public Item update(Item item) {
		return null;
	}

	@Override
	public Item delete(Item item) {
		return null;
	}

	@Override
	public void remove(int itemId, int removedReasonId, int userId) {
		
		RemovedReason reason = em.find(RemovedReason.class, removedReasonId);
		BasicUserDetails user = em.find(BasicUserDetails.class, userId);
		Date date = new Date();
		
		Item item = (Item)em.find(Item.class, itemId);
		
		item.setRemovedReason(reason);
		item.setRemovedBy(user);
		item.setDateRemoved(date);
		item.setAvailable(false);
	}
	
	@Override
	public void undoRemove(int itemId) {
		
		Item item = em.find(Item.class, itemId);
		item.setDateRemoved(null);
		item.setRemovedReason(null);
		item.setRemovedBy(null);
		item.setAvailable(true);
		
	}

	@Override
	public List<RemovedReason> listRemovalReasons() {
		return em.createQuery("From RemovedReason").getResultList();
	}

	@Override
	public Location getItemLocation(int id) {
		List<Location> location = em.createQuery("SELECT item.location FROM Item as item WHERE item.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList();
		return location.size() == 1 ? location.get(0) : null;
	}

	@Override
	public Stockroom getItemStockroom(int id) {
		List<Stockroom> stockroom = em.createQuery("SELECT item.stockroom FROM Item as item WHERE item.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList();
		return stockroom.size() == 1 ? stockroom.get(0) : null;
	}

	@Override
	public ItemCondition getItemCondition(int id) {
		List<ItemCondition> itemCondition = em.createQuery("SELECT item.itemCondition FROM Item as item WHERE item.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList();
		return itemCondition.size() == 1 ? itemCondition.get(0) : null;
	}

	@Override
	public Boolean isAvailable(int id) {
		List<Boolean> availability = em.createQuery("SELECT item.isAvailable FROM Item as item WHERE item.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList();
		return availability.size() == 1 ? availability.get(0) : null;
	}
	
	
	
}
