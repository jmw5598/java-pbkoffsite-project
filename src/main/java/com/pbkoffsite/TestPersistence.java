package com.pbkoffsite;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pbkoffsite.config.BeanConfig;
import com.pbkoffsite.web.bean.Sku;
import com.pbkoffsite.web.util.ShelfAuditUtil;

public class TestPersistence {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BeanConfig.class);
		
		EntityManagerFactory emf = context.getBean("entityManagerFactory", EntityManagerFactory.class);
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Sku> skus = em.createQuery("SELECT i.sku FROM Item AS i WHERE i.location.id = 1 AND i.stockroom.id = 2 AND i.isAvailable = true").getResultList();
		em.getTransaction().commit();
		emf.close();
		
		List<Sku> skus2 = new LinkedList<>();
		for(Sku sku : skus) {
			skus2.add(sku);
		}
		
		
		Sku sku = skus.get(0);
		Sku sku2 = skus.get(6);
		skus2.add(sku);
		skus2.remove(sku2);
		
		Map<Sku, Integer> results = ShelfAuditUtil.difference(skus, skus2);
		
		for(Map.Entry<Sku, Integer> entry : results.entrySet()) {
			System.out.println("Sku: " + entry.getKey().getId());
			System.out.println("Description: " + entry.getKey().getDescription());
			System.out.println("Difference: " + entry.getValue());
			System.out.println();
		}
		
	}

}
