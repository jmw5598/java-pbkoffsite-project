package com.pbkoffsite;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pbkoffsite.web.bean.entity.Location;
import com.pbkoffsite.web.bean.entity.Item;
import com.pbkoffsite.web.bean.entity.User;
import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.repository.hibernate.ItemRepositoryImpl;
import com.pbkoffsite.web.repository.hibernate.StockroomRepositoryImpl;
import com.pbkoffsite.config.BeanConfig;

public class TestPersistence {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BeanConfig.class);
		
		StockroomRepositoryImpl repository = context.getBean("stockroomRepositoryImpl", StockroomRepositoryImpl.class);
		
		List<Stockroom> stockrooms = repository.list();
		
		//List<Item> items = stockroom.getItems();
		
		for(Stockroom s : stockrooms)
			System.out.println(s);
	}

}
