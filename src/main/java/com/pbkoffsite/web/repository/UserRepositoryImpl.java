package com.pbkoffsite.web.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pbkoffsite.web.bean.User;
import com.pbkoffsite.web.util.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserByUsername(String username) {
		
		String query = "select u.id as id, u.username as username, u.first_name as first_name, u.last_name from user as u where username = ?;";
		User user = jdbcTemplate.queryForObject(query, new Object[]{username}, userMapper);
		
		return user;
	}

	@Override
	public User findUserById(int id) {
		
		String query = "select u.id as id, u.username as username, u.first_name as first_name, u.last_name from user as u where id = ?;";
		User user = jdbcTemplate.queryForObject(query, new Object[]{id}, userMapper);
		
		return user;
	}

	@Override
	public List<User> listUsers() {
		
		String query = "select u.id as id, u.username as username, u.first_name as first_name, u.last_name from user as u;";
		List<User> users = jdbcTemplate.query(query, userMapper);
		
		return users;
	}

}
