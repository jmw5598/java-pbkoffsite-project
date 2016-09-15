package com.pbkoffsite.web.repository;

import java.util.List;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.ItemCondition;
import com.pbkoffsite.web.bean.Location;
import com.pbkoffsite.web.bean.RemovedReason;
import com.pbkoffsite.web.bean.Stockroom;

public interface ItemRepository {
	List<Item> list();
	List<Item> listAvailable();
	List<Item> listByStockroomId(int id);
	List<Item> listRecentlyAdded();
	List<Item> listSimilar(Item item);
	List<Item> listFloormodel();
	List<Item> listRemoved();
	List<RemovedReason> listRemovalReasons();
	Item findById(int id);
	Item update(Item item);
	Item delete(Item item);
	void remove(int itemId, int removedReasonId, int userId);
	void undoRemove(int itemId);
	Location getItemLocation(int id);
	Stockroom getItemStockroom(int id);
	ItemCondition getItemCondition(int id);
	Boolean isAvailable(int id);
}
