package com.pbkoffsite.web.util;

import com.pbkoffsite.web.bean.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Role role = new Role();
		role.setRole(rs.getString("role"));
		
		return role;
	}

}
