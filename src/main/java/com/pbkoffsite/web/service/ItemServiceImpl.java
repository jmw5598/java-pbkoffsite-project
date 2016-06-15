package com.pbkoffsite.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.SimpleIdDescription;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> listAllItems() {
		
		return itemRepository.listAllItems();
	}

	@Override
	public List<Item> listItemsByStockroom(String stockroom) {
		
		return itemRepository.listItemsByStockroom(stockroom);
	}

	@Override
	public Item findItemById(int id) {
		
		return itemRepository.findItemById(id);
		
	}

	@Override
	public List<Item> listRecentlyAddedItems() {
		
		return itemRepository.listRecentlyAddedItems();
		
	}

	@Override
	public List<Item> listFloormodelItems() {
		
		return itemRepository.listFloodmodelItems();
		
	}

	@Override
	public List<Stockroom> countItemsByStockroom() {
		
		return itemRepository.countItemsByStockroom();
		
	}

	@Override
	public Stockroom countItemsByStockroom(String stockroom) {
		
		return itemRepository.countItemsByStockroom(stockroom);
		
	}

	@Override
	public List<String> findItemImagesById(int id) {
		
		return itemRepository.findItemImagesById(id);
		
	}

	@Override
	public List<Item> listSimilarItems(Item item) {
		
		return itemRepository.listSimilarItems(item);
		
	}

	@Override
	public List<SimpleIdDescription> listItemRemovedReasons() {
		
		return itemRepository.listItemRemovedReasons();
		
	}

	@Override
	public int removeItem(int id, int removed_reason_id, int user_id) {
		
		return itemRepository.removeItem(id, removed_reason_id, user_id);
		
	}

	@Override
	public int undoRemoveItem(int id) {
		
		return itemRepository.undoRemoveItem(id);
		
	}
	
	
	
}
