package com.pbkoffsite.web.repository.hibernate;

import java.util.List;

import com.pbkoffsite.web.bean.entity.Stockroom;

public interface StockroomRepository {
	List<Stockroom> list();
	List<Stockroom> list(boolean listItems);
	Stockroom findById(int id);
	Stockroom findById(int id, boolean listItems);
	Long countItemsByStockroomId(int id);
}
