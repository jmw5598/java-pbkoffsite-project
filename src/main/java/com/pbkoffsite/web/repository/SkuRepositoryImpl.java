package com.pbkoffsite.web.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.Sku;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SkuRepositoryImpl implements SkuRepository {
	
	@PersistenceUnit
	EntityManagerFactory emf;

	@Override
	public List<Sku> list() {
		return emf.createEntityManager()
				.createQuery("FROM Sku")
				.getResultList();
	}

	@Override
	public Sku find(int id) {
		
		List<Sku> skus = emf.createEntityManager()
				.createQuery("FROM Sku as sku WHERE sku.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList();
		
		return skus.get(0);
	}

	@Override
	public Sku create(Sku sku) {
		emf.createEntityManager().persist(sku);
		return sku;
	}
	
}
