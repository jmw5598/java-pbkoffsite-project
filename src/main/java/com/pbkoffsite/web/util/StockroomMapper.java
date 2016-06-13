package com.pbkoffsite.web.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pbkoffsite.web.bean.Stockroom;

public class StockroomMapper implements RowMapper<Stockroom> {

	@Override
	public Stockroom mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Stockroom stockroom = new Stockroom();
		stockroom.setId(rs.getInt("id"));
		stockroom.setDescription(rs.getString("description"));
		stockroom.setCount(rs.getInt("count"));
		
		return stockroom;
	}

}
