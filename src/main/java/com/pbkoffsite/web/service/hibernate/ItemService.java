package com.pbkoffsite.web.service.hibernate;

import java.util.List;

import com.pbkoffsite.web.bean.entity.Item;

public interface ItemService {
	
	List<Item> list();
	List<Item> listAvailable();
	List<Item> listByStockroomId(int id);
	List<Item> listRecentlyAdded();
	List<Item> listSimilar(Item item);
	List<Item> listFloormodel();
	Item findById(int id);
	Item update(Item item);
	Item delete(Item item);
	Item remove(Item item);
	
}
