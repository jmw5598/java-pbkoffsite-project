package com.pbkoffsite;


import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pbkoffsite.web.bean.entity.AuthUserDetails;
import com.pbkoffsite.web.bean.entity.Location;
import com.pbkoffsite.web.bean.entity.Item;
import com.pbkoffsite.web.bean.entity.BasicUserDetails;
import com.pbkoffsite.web.bean.entity.Role;
import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.repository.hibernate.ItemRepositoryImpl;
import com.pbkoffsite.web.repository.hibernate.StockroomRepositoryImpl;
import com.pbkoffsite.web.repository.hibernate.UserRepositoryImpl;
import com.pbkoffsite.config.BeanConfig;

public class TestPersistence {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BeanConfig.class);
		
		ItemRepositoryImpl repository = context.getBean("itemRepositoryImpl", ItemRepositoryImpl.class);
		
		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		
		
		Item item = repository.findById(1);
		
		System.out.println(item);
	
		
	}

}
