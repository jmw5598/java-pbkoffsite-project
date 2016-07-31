package com.pbkoffsite.web.service;

import java.util.List;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.UserForm;

public interface MyUserDetailsService {
	List<AuthUserDetails> list();
	List<Role> listRoles();
	void create(UserForm user); 
	void toggleEnabled(int id);
}
