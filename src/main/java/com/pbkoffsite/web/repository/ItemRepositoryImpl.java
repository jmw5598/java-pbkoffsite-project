package com.pbkoffsite.web.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.SimpleIdDescription;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.util.ItemMapper;
import com.pbkoffsite.web.util.SimpleIdDescriptionMapper;
import com.pbkoffsite.web.util.StockroomMapper;

//@Repository
public class ItemRepositoryImpl implements ItemRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private StockroomMapper stockroomMapper;
	
	@Autowired
	private SimpleIdDescriptionMapper simpleIdDescriptionMapper;
	
	@Value("${sql.item.listAllItems}")
	String listAllItems;
	
	@Value("${sql.item.listItemsByStockroom}")
	String listItemsByStockroom;
	
	@Value("${sql.item.findItemById}")
	String findItemById;
	
	@Value("${sql.item.listRecentlyAddedItems}")
	String listRecentlyAddedItems;
	
	@Value("${sql.item.listFloodmodelItems}")
	String listFloodmodelItems;
	
	@Value("${sql.item.countItemsByStockroom}")
	String countItemsByStockroom;
	
	@Value("${sql.item.countItemsByStockroomName}")
	String countItemsByStockroomName;
	
	@Value("${sql.item.listSimilarItems}")
	String listSimilarItems;
	
	@Value("${sql.item.removeItem}")
	String removeItem;
	
	@Value("${sql.item.undoRemoveItem}")
	String undoRemoveItem;
	
	@Override
	public List<Item> listAllItems() {
		
		List<Item> items = jdbcTemplate.query(listAllItems, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public List<Item> listItemsByStockroom(String stockroom) {
		
		List<Item> items = jdbcTemplate.query(listItemsByStockroom, new Object[]{stockroom}, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public Item findItemById(int id) {
		
		Item item = jdbcTemplate.queryForObject(findItemById, new Object[]{id}, itemMapper);
		item.setImageUrls(findItemImagesById(id));
		
		return item;
	}

	
	@Override
	public Item findItemByIdAndStockroom(int id, String stockroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> listRecentlyAddedItems() {
		
		List<Item> items = jdbcTemplate.query(listRecentlyAddedItems, itemMapper);
		
		return items;
	}

	@Override
	public List<Item> listFloodmodelItems() {
		
		List<Item> items = jdbcTemplate.query(listFloodmodelItems, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public List<Stockroom> countItemsByStockroom() {
		
		List<Stockroom> stockroomCounts = jdbcTemplate.query(countItemsByStockroom, stockroomMapper);
		
		return stockroomCounts;
	}

	@Override
	public Stockroom countItemsByStockroom(String stockroom) {
		
		Stockroom stockroomCount = jdbcTemplate.queryForObject(countItemsByStockroomName, new Object[]{stockroom}, stockroomMapper);
		
		return stockroomCount;
	}

	@Override
	public List<String> findItemImagesById(int id) {
		
		String query = "select im.location_url as url from item as i inner join item_image as itim on i.id = itim.item_id inner join image as im on im.id = itim.image_id where i.id = 1;";
		List<String> itemImages = jdbcTemplate.queryForList(query, String.class);
		
		return itemImages;
	}

	@Override
	public List<Item> listSimilarItems(Item item) {
		
		List<Item> similarItems = jdbcTemplate.query(listSimilarItems, new Object[]{item.getSku(), item.getId()}, itemMapper);
		
		for(Item i : similarItems) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return similarItems;
	}

	@Override
	public List<SimpleIdDescription> listItemRemovedReasons() {
		
		String query = "select * from removed_reason;";
		List<SimpleIdDescription> removedReasons = jdbcTemplate.query(query, simpleIdDescriptionMapper);
		
		return removedReasons;
	}

	@Override
	public int removeItem(int id, int removed_reason_id, int user_id) {
		
		return jdbcTemplate.update(removeItem, new Object[]{user_id, removed_reason_id, id});
		
	}

	@Override
	public int undoRemoveItem(int id) {
		
		return jdbcTemplate.update(undoRemoveItem, new Object[]{id});
		
	}
	
	
}
