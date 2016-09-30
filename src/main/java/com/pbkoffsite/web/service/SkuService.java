package com.pbkoffsite.web.service;

import java.util.List;

import com.pbkoffsite.web.bean.Sku;

public interface SkuService {
	
	List<Sku> list();
	Sku find(int id);
	Sku create(Sku sku);
	
}
