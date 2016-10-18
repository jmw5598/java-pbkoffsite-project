package com.pbkoffsite.web.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.Stockroom;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class StockroomRepositoryImpl implements StockroomRepository{
	
	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public List<Stockroom> list() {
		
		List<Stockroom> stockrooms = em.createQuery("FROM Stockroom")
										.getResultList();
		
		for(Stockroom s : stockrooms) {
			s.setCount(this.countItemsByStockroomId(s.getId()));
		}
		
		return stockrooms;
		
	}

	@Override
	public List<Stockroom> list(boolean listItems) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Stockroom findById(int id) {
		return this.findById(id, false);
	}
	
	@Override
	public Stockroom findById(int id, boolean listItems) {
		
		Stockroom stockroom = em.find(Stockroom.class, id);
		stockroom.setCount(countItemsByStockroomId(id));
		
		if(listItems)
			stockroom.getItems().size();
		
		return stockroom;
	}

	@Override
	public Long countItemsByStockroomId(int id) {
		
		return (Long)em.createQuery("SELECT COUNT(*) FROM Item as item WHERE item.stockroom.id = :id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Item> listItems(int id) {
		return em.createQuery("FROM Item as item WHERE item.stockroom.id = :id AND item.isAvailable = true")
				.setParameter("id", id)
				.getResultList();
	}

}
