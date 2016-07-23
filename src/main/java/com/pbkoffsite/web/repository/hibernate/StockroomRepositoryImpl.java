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
		
		return emf.createEntityManager().createQuery("FROM Stockroom AS stockroom").getResultList();
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
		
		if(listItems)
			stockroom.getItems().size();
		
		return stockroom;
	}

}
