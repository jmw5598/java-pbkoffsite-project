package com.pbkoffsite.web.service;

import java.util.List;

import com.pbkoffsite.web.bean.User;

public interface UserService {
	
	User findUserByUsername(String username);
	User findUserById(int id);
	List<User> listUsers();

}
