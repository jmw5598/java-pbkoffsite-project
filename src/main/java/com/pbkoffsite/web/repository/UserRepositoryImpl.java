package com.pbkoffsite.web.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pbkoffsite.web.bean.Role;
import com.pbkoffsite.web.bean.User;
import com.pbkoffsite.web.util.RoleMapper;
import com.pbkoffsite.web.util.UserMapper;

//@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public User findUserByUsername(String username) {
		
		String query = "select u.id as id, u.username as username, u.password as password, u.first_name as first_name, u.last_name as last_name from user as u where username = ?;";
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

	@Override
	public Collection<Role> findRolesByUsername(String username) {
		
		String query = "select r.role from user as u inner join user_role as ur on u.id = ur.user_id inner join role as r on r.id = ur.role_id where username = ?;";
		Collection<Role> roles = jdbcTemplate.query(query, new Object[]{username}, roleMapper);
		
		return roles;
	}

}
