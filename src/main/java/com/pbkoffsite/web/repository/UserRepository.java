package com.pbkoffsite.web.repository;

import java.util.List;

import com.pbkoffsite.web.bean.User;

public interface UserRepository {
	
	User findUserByUsername(String username);
	User findUserById(int id);
	List<User> listUsers();
	
}
