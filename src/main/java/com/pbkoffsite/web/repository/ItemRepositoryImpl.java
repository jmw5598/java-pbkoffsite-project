package com.pbkoffsite.web.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pbkoffsite.web.bean.Item;
import com.pbkoffsite.web.bean.SimpleIdDescription;
import com.pbkoffsite.web.bean.Stockroom;
import com.pbkoffsite.web.util.ItemMapper;
import com.pbkoffsite.web.util.SimpleIdDescriptionMapper;
import com.pbkoffsite.web.util.StockroomMapper;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private StockroomMapper stockroomMapper;
	
	@Autowired
	private SimpleIdDescriptionMapper simpleIdDescriptionMapper;
	
	@Override
	public List<Item> listAllItems() {
		
		String query = "select i.id as id, u.username as added_by, i.date_added as date_added, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on u.id = i.added_by_id order by s.description ASC;";
		List<Item> items = jdbcTemplate.query(query, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public List<Item> listItemsByStockroom(String stockroom) {
		
		String query = "select i.id as id, u.username as added_by, i.date_added as date_added, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on u.id = i.added_by_id where sr.description=? AND i.is_available = 1 order by s.description ASC;";
		List<Item> items = jdbcTemplate.query(query, new Object[]{stockroom}, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public Item findItemById(int id) {
		
		String query = "select i.date_added as date_added, u.username as added_by, i.id as id, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on i.added_by_id = u.id where i.id=?;";
		Item item = jdbcTemplate.queryForObject(query, new Object[]{id}, itemMapper);
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
		
		String query = "select i.id as id, i.date_added as date_added, u.username as added_by, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on u.id = i.added_by_id where i.is_available = 1 order by i.date_added DESC LIMIT 15;";
		List<Item> items = jdbcTemplate.query(query, itemMapper);
		
		return items;
	}

	@Override
	public List<Item> listFloodmodelItems() {
		
		String query = "select i.id as id, i.date_added as date_added, u.username as added_by, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on u.id = i.added_by_id where ic.description != 'new in box' AND ic.description != 'new out of box' AND i.is_available = 1;";
		List<Item> items = jdbcTemplate.query(query, itemMapper);
		
		for(Item i : items) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		return items;
	}

	@Override
	public List<Stockroom> countItemsByStockroom() {
		
		String query = "select s.id, s.description, count(i.stockroom_id) as count from item as i right join stockroom as s on i.stockroom_id = s.id where i.is_available = 1 group by i.stockroom_id;";
		List<Stockroom> stockroomCounts = jdbcTemplate.query(query, stockroomMapper);
		
		return stockroomCounts;
	}

	@Override
	public Stockroom countItemsByStockroom(String stockroom) {
		
		String query = "select s.id, s.description, count(i.stockroom_id) as count from item as i inner join stockroom as s on i.stockroom_id = s.id where s.description = ? AND i.is_available = 1 group by i.stockroom_id;";
		Stockroom stockroomCount = jdbcTemplate.queryForObject(query, new Object[]{stockroom}, stockroomMapper);
		
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
		
		String query = "select i.id as id, i.date_added as date_added, u.username as added_by, s.id as sku, s.description as description, sr.description as stockroom, l.description as location, ic.description as item_condition, i.additional_notes as additional_notes from item as i inner join sku as s on i.sku_id = s.id inner join stockroom as sr on i.stockroom_id = sr.id inner join location as l on i.location_id = l.id inner join item_condition ic on i.item_condition_id = ic.id inner join user as u on u.id = i.added_by_id where s.id = ? AND i.id != ? AND i.is_available = 1;";
		List<Item> similarItems = jdbcTemplate.query(query, new Object[]{item.getSku(), item.getId()}, itemMapper);
		
		for(Item i : similarItems) {
			i.setImageUrls(findItemImagesById(i.getId()));
		}
		
		System.out.println(similarItems.size());
		
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
		
		String query = "update item as i set i.date_removed = CURRENT_TIMESTAMP, i.removed_by_id = ?, i.removed_reason_id = ?, i.is_available = 0 where i.id = ?;";
		
		return jdbcTemplate.update(query, new Object[]{user_id, removed_reason_id, id});
		
	}
	
	
}
