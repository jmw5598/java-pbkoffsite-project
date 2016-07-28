package com.pbkoffsite.web.repository;

import java.util.Collection;
import java.util.List;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.UserForm;

public interface UserRepository {
	
	List<AuthUserDetails> list();
	List<Role> listRoles();
	AuthUserDetails findUserByUsername(String username);
	Collection<Role> findRolesByUsername(String username);
	AuthUserDetails create(UserForm user);
	AuthUserDetails update(AuthUserDetails user);
	
}
