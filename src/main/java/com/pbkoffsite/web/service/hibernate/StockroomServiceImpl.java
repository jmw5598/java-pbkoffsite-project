package com.pbkoffsite.web.service.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.repository.hibernate.StockroomRepository;

@Service
public class StockroomServiceImpl implements StockroomService {
	
	@Autowired
	private StockroomRepository stockroomRepository;
	
	@Override
	public List<Stockroom> list() {
		return stockroomRepository.list();
	}

	@Override
	public List<Stockroom> list(boolean listItems) {
		return stockroomRepository.list(listItems);
	}

	@Override
	public Stockroom findById(int id) {
		return stockroomRepository.findById(id);
	}

	@Override
	public Stockroom findById(int id, boolean listItems) {
		return stockroomRepository.findById(id, listItems);
	}

	@Override
	public Long countItemsByStockroomId(int id) {
		return stockroomRepository.countItemsByStockroomId(id);
	}

}
