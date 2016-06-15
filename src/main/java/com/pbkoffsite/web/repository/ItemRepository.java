package com.pbkoffsite.web.repository;

import java.util.List;
import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.SimpleIdDescription;
import com.pbkoffsite.web.bean.Stockroom;

public interface ItemRepository {
	
	List<Item> listAllItems();
	List<Item> listItemsByStockroom(String stockroom);
	Item findItemById(int id);
	Item findItemByIdAndStockroom(int id, String stockroom);
	List<String> findItemImagesById(int id);
	List<Item> listRecentlyAddedItems();
	List<Item> listFloodmodelItems();
	List<Stockroom> countItemsByStockroom();
	Stockroom countItemsByStockroom(String stockroom);
	List<Item> listSimilarItems(Item item);
	List<SimpleIdDescription> listItemRemovedReasons();
	int removeItem(int id, int removed_reason_id, int user_id);
	int undoRemoveItem(int id);

}
