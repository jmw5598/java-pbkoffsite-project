package com.pbkoffsite;


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
import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.repository.hibernate.ItemRepositoryImpl;
import com.pbkoffsite.web.repository.hibernate.StockroomRepositoryImpl;
import com.pbkoffsite.config.BeanConfig;

public class TestPersistence {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BeanConfig.class);
		
		StockroomRepositoryImpl repository = context.getBean("stockroomRepositoryImpl", StockroomRepositoryImpl.class);
		
		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		
		List<AuthUserDetails> users = emf.createEntityManager().createQuery("FROM CustomUserDetails").getResultList();
		
		
		for(AuthUserDetails u : users)
			System.out.println(u);
		
		System.out.println(users.size());
	}

}
