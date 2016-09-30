package com.pbkoffsite.web.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pbkoffsite.web.bean.Sku;

public interface SkuRepository {
	
	List<Sku> list();
	Sku find(int id);
	Sku create(Sku sku);
	
}
