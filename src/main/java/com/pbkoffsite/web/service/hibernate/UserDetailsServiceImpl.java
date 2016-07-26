package com.pbkoffsite.web.service.hibernate;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.entity.AuthUserDetails;
import com.pbkoffsite.web.bean.entity.Role;
import com.pbkoffsite.web.repository.hibernate.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
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

}
