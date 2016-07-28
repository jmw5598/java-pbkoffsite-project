package com.pbkoffsite.web.repository;

import java.util.List;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.RemovedReason;

public interface ItemRepository {
	List<Item> list();
	List<Item> listAvailable();
	List<Item> listByStockroomId(int id);
	List<Item> listRecentlyAdded();
	List<Item> listSimilar(Item item);
	List<Item> listFloormodel();
	List<RemovedReason> listRemovalReasons();
	Item findById(int id);
	Item update(Item item);
	Item delete(Item item);
	void remove(int itemId, int removedReasonId, int userId);
}
