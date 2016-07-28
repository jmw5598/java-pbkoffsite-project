package com.pbkoffsite.web.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.BasicUserDetails;
import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.RemovedReason;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ItemRepositoryImpl implements ItemRepository {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Override
	public List<Item> list() {
		
		return emf.createEntityManager().createQuery("from Item").getResultList();
		
	}
	
	@Override
	public List<Item> listAvailable() {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE item.isAvailable = true")
				.getResultList();
		
	}

	@Override
	public List<Item> listByStockroomId(int id) {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE item.stockroom.id = :id AND item.isAvailable = true")
				.setParameter("id", id)
				.getResultList();
		
	}

	
	@Override
	public List<Item> listRecentlyAdded() {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE item.isAvailable = true ORDER BY item.dateAdded DESC")
				.setFirstResult(0)
				.setMaxResults(15)
				.getResultList();
	}

	@Override
	public List<Item> listSimilar(Item item) {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE item.sku.id = :skuId AND item.id != :itemId AND item.isAvailable = true")
				.setParameter("skuId", item.getSku().getId())
				.setParameter("itemId", item.getId())
				.getResultList();
		
	}

	@Override
	public List<Item> listFloormodel() {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE item.itemCondition.id != 1 AND item.itemCondition.id != 2 AND item.isAvailable = true")
				.getResultList();
	}
	
	@Override
	public List<Item> listRemoved() {
		
		return emf.createEntityManager()
				.createQuery("FROM Item AS item WHERE isAvailable = false ORDER BY item.dateRemoved DESC")
				.getResultList();
	}

	@Override
	public Item findById(int id) {
		
		return emf.createEntityManager()
				  .find(Item.class, id);
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
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		RemovedReason reason = em.find(RemovedReason.class, removedReasonId);
		BasicUserDetails user = em.find(BasicUserDetails.class, userId);
		Date date = new Date();
		
		Item item = (Item)em.find(Item.class, itemId);
		
		item.setRemovedReason(reason);
		item.setRemovedBy(user);
		item.setDateRemoved(date);
		item.setAvailable(false);
		
		em.persist(item);
		em.getTransaction().commit();
	}
	
	@Override
	public void undoRemove(int itemId) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Item item = em.find(Item.class, itemId);
		item.setDateRemoved(null);
		item.setRemovedReason(null);
		item.setRemovedBy(null);
		item.setAvailable(true);
		
		em.persist(item);
		em.getTransaction().commit();
		
		
	}

	@Override
	public List<RemovedReason> listRemovalReasons() {
		return emf.createEntityManager()
				.createQuery("From RemovedReason").getResultList();
	}
	
	
	
}
