package com.pbkoffsite.web.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.UserForm;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public AuthUserDetails findUserByUsername(String username) {
		
		AuthUserDetails user =  (AuthUserDetails) em.createQuery("FROM AuthUserDetails AS u WHERE u.username = :username")
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

	@Override
	public AuthUserDetails create(UserForm user) {
			
		Role role = (Role)em.createQuery("FROM Role as role WHERE role.role = :userRole")
				.setParameter("userRole", user.getRole())
				.getSingleResult();
		AuthUserDetails newUser = new AuthUserDetails();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.addRole(role);
		
		return newUser;
	}

	@Override
	public AuthUserDetails update(AuthUserDetails user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AuthUserDetails> list() {
		return em.createQuery("FROM AuthUserDetails As user")
				.getResultList();
	}
	
	@Override
	public List<Role> listRoles() {
		return em.createQuery("FROM Role AS role")
				.getResultList();
	}
	
	@Override
	public void toggleEnabled(int id) {
			
		AuthUserDetails user = em.find(AuthUserDetails.class, id);
		user.setEnabled(!(user.isEnabled()));
		
	}
	
}
