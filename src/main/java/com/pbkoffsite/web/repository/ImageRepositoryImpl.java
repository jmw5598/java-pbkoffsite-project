package com.pbkoffsite.web.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ImageRepositoryImpl implements ImageRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> findImageUrlsByItemId(int id) {
		
		String query = "select im.location_url as url from item as i inner join item_image as itim on i.id = itim.item_id inner join image as im on im.id = itim.image_id where i.id = 1;";
		List<String> images = jdbcTemplate.queryForList(query, String.class);
		
		return images;
	}

}
