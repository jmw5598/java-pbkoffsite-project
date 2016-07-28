package com.pbkoffsite.web.service;

import java.util.List;

import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.UserForm;

public interface MyUserDetailsService {
	
	List<Role> listRoles();
	void create(UserForm user); 
	
}
