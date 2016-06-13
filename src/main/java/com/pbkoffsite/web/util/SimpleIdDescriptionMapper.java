package com.pbkoffsite.web.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pbkoffsite.web.bean.SimpleIdDescription;

public class SimpleIdDescriptionMapper implements RowMapper<SimpleIdDescription> {

	@Override
	public SimpleIdDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		SimpleIdDescription row = new SimpleIdDescription();
		row.setId(rs.getInt("id"));
		row.setDescription(rs.getString("description"));
		
		return row;
	}
	
	
}
