package com.pbkoffsite.web.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.UserForm;
import com.pbkoffsite.web.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, MyUserDetailsService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		AuthUserDetails user = userRepository.findUserByUsername(username);
		user.setRoles(userRepository.findRolesByUsername(username));
		
		if(user == null)
			throw new UsernameNotFoundException("Username not found");
		
		return user;
	}
	
	@Override
	public List<Role> listRoles() {
		return userRepository.listRoles();
	}
	
	@Override
	public void create(UserForm user) {
		userRepository.create(user);
	}

	@Override
	public List<AuthUserDetails> list() {
		return userRepository.list();
	}

	@Override
	public void toggleEnabled(int id) {
		userRepository.toggleEnabled(id);
	}
	

}
