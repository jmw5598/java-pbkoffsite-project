package com.pbkoffsite.web.repository.hibernate;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.entity.Stockroom;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class StockroomRepositoryImpl implements StockroomRepository{
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
	
	@Override
	public List<Stockroom> list() {
		
		List<Stockroom> stockrooms = emf.createEntityManager()
										.createQuery("FROM Stockroom")
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
		
		Stockroom stockroom = emf.createEntityManager()
								 .find(Stockroom.class, id);
		stockroom.setCount(countItemsByStockroomId(id));
		
		if(listItems)
			stockroom.getItems().size();
		
		return stockroom;
	}

	@Override
	public Long countItemsByStockroomId(int id) {
		
		return (Long)emf.createEntityManager()
				.createQuery("SELECT COUNT(*) FROM Item as item WHERE item.stockroom.id = :id")
				.setParameter("id", id).getSingleResult();
	}

}
