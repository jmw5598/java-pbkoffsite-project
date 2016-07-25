package com.pbkoffsite.web.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.entity.Item;

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
				  .createQuery("FROM Item AS item ORDER BY item.dateAdded DESC")
				  .setFirstResult(0)
				  .setMaxResults(15)
				  .getResultList();
	}

	@Override
	public List<Item> listSimilar(Item item) {
		
		return emf.createEntityManager()
				  .createQuery("FROM Item AS item WHERE item.sku.id = :skuId AND item.id != :itemId")
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
	public Item remove(Item item) {
		
		return null;
	}
	
	
	
}
