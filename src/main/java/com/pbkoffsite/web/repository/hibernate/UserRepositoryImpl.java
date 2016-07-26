package com.pbkoffsite.web.repository.hibernate;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.entity.Role;
import com.pbkoffsite.web.bean.entity.AuthUserDetails;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Override
	public AuthUserDetails findUserByUsername(String username) {
		
		AuthUserDetails user =  (AuthUserDetails) emf.createEntityManager()
				.createQuery("FROM AuthUserDetails AS u WHERE u.username = :username")
				.setParameter("username", username)
				.getSingleResult();
		user.getRoles().size();
		
		return user;
	}

	@Override
	public Collection<Role> findRolesByUsername(String username) {
		
		AuthUserDetails user = this.findUserByUsername(username);
		
		return user.getRoles();
	}
	
}
