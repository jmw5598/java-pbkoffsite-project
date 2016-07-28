package com.pbkoffsite.web.repository;

import java.util.Collection;
import java.util.List;

import com.pbkoffsite.web.bean.AuthUserDetails;
import com.pbkoffsite.web.bean.Role;

public interface UserRepository {
	
	AuthUserDetails findUserByUsername(String username);
	Collection<Role> findRolesByUsername(String username);
	
}
