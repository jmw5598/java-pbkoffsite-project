package com.pbkoffsite.web.repository.hibernate;

import java.util.Collection;
import java.util.List;

import com.pbkoffsite.web.bean.entity.AuthUserDetails;
import com.pbkoffsite.web.bean.entity.Role;

public interface UserRepository {
	
	AuthUserDetails findUserByUsername(String username);
	Collection<Role> findRolesByUsername(String username);
	
}
