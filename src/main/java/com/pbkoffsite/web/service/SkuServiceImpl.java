package com.pbkoffsite.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkoffsite.web.bean.Sku;
import com.pbkoffsite.web.repository.SkuRepository;

@Service
public class SkuServiceImpl implements SkuService{
	
	@Autowired
	private SkuRepository skuRepository;
	
	@Override
	public List<Sku> list() {
		return skuRepository.list();
	}

	@Override
	public Sku find(int id) {
		return skuRepository.find(id);
	}

	@Override
	public Sku create(Sku sku) {
		return skuRepository.create(sku);
	}

}
