package com.pbkoffsite.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.ItemCondition;
import com.pbkoffsite.web.bean.Location;
import com.pbkoffsite.web.bean.RemovedReason;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> list() {
		return itemRepository.list();
	}

	@Override
	public List<Item> listAvailable() {
		return itemRepository.listAvailable();
	}

	@Override
	public List<Item> listByStockroomId(int id) {
		return itemRepository.listByStockroomId(id);
	}

	@Override
	public List<Item> listRecentlyAdded() {
		return itemRepository.listRecentlyAdded();
	}

	@Override
	public List<Item> listSimilar(Item item) {
		return itemRepository.listSimilar(item);
	}

	@Override
	public List<Item> listFloormodel() {
		return itemRepository.listFloormodel();
	}
	
	@Override
	public List<Item> listRemoved() {
		return itemRepository.listRemoved();
	}

	@Override
	public Item findById(int id) {
		return itemRepository.findById(id);
	}

	@Override
	public Item update(Item item) {
		return itemRepository.update(item);
	}

	@Override
	public Item delete(Item item) {
		return itemRepository.delete(item);
	}

	@Override
	public void remove(int itemId, int removedReasonId, int userId) {
		itemRepository.remove(itemId, removedReasonId, userId);
	}
	
	@Override
	public void undoRemove(int itemId) {
		itemRepository.undoRemove(itemId);
	}

	@Override
	public List<RemovedReason> listRemovalReasons() {
		return itemRepository.listRemovalReasons();
	}

	@Override
	public Location getItemLocation(int id) {
		return itemRepository.getItemLocation(id);
	}

	@Override
	public Stockroom getItemStockroom(int id) {
		return itemRepository.getItemStockroom(id);
	}

	@Override
	public ItemCondition getItemCondition(int id) {
		return itemRepository.getItemCondition(id);
	}

	@Override
	public Boolean isAvailable(int id) {
		return itemRepository.isAvailable(id);
	}
	
	
	
}
