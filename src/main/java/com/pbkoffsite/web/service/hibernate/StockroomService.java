package com.pbkoffsite.web.service.hibernate;

import java.util.List;

import com.pbkoffsite.web.bean.entity.Stockroom;

public interface StockroomService {
	
	List<Stockroom> list();
	List<Stockroom> list(boolean listItems);
	Stockroom findById(int id);
	Stockroom findById(int id, boolean listItems);
	Long countItemsByStockroomId(int id);
	
}
