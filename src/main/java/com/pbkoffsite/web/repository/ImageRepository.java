package com.pbkoffsite.web.repository;

import java.util.List;

public interface ImageRepository {

	List<String> findImageUrlsByItemId(int id);
	
}
