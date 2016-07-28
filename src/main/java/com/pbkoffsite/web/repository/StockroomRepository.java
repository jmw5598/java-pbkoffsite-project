package com.pbkoffsite.web.repository;

import java.util.List;

import com.pbkoffsite.web.bean.Stockroom;

public interface StockroomRepository {
	List<Stockroom> list();
	List<Stockroom> list(boolean listItems);
	Stockroom findById(int id);
	Stockroom findById(int id, boolean listItems);
	Long countItemsByStockroomId(int id);
}
