package com.pbkoffsite.web.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pbkoffsite.web.bean.Item;

public class ItemMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setDateAdded(rs.getTimestamp("date_added"));
		item.setAddedBy(rs.getString("added_by"));
		item.setSku(rs.getInt("sku"));
		item.setDescription(rs.getString("description"));
		item.setStockroom(rs.getString("stockroom"));
		item.setLocation(rs.getString("location"));
		item.setCondition(rs.getString("item_condition"));
		item.setAdditionalNotes(rs.getString("additional_notes"));
		
		return item;
		
	}

}
