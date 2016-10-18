package com.pbkoffsite.web.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.ShelfAudit;

@Repository
@Transactional
public class ShelfAuditRepositoryImpl implements ShelfAuditRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ShelfAudit find(Integer id) {
		
		return null;
	}

	@Override
	public ShelfAudit create(Integer stockroomId, Integer locationId) {
		
		return null;
	}

	@Override
	public ShelfAudit update(Integer skuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShelfAudit remove(Integer skuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShelfAudit complete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
