package com.pbkoffsite.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionManagersConfig {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		tm.setDataSource(dataSource);
		return tm;
		
	}
	
}
