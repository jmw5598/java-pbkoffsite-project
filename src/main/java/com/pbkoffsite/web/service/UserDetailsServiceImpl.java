package com.pbkoffsite.web.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pbkoffsite.web.bean.CustomUserDetails;
import com.pbkoffsite.web.bean.User;
import com.pbkoffsite.web.repository.UserRepository;
import com.pbkoffsite.web.bean.Role;

//@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByUsername(username);
		user.setRoles(userRepository.findRolesByUsername(username));
		
		if(user == null)
			throw new UsernameNotFoundException("Username not found");
		
		return new CustomUserDetails(user);
	}

}
