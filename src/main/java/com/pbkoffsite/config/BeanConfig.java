package com.pbkoffsite.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.pbkoffsite.web.bean.entity.ItemCondition;
import com.pbkoffsite.web.bean.entity.Stockroom;
import com.pbkoffsite.web.bean.entity.Item;
import com.pbkoffsite.web.bean.entity.Location;
import com.pbkoffsite.web.bean.entity.RemovedReason;
import com.pbkoffsite.web.bean.entity.Role;
import com.pbkoffsite.web.bean.entity.Sku;
import com.pbkoffsite.web.bean.entity.User;
import com.pbkoffsite.web.repository.hibernate.ItemRepositoryImpl;
import com.pbkoffsite.web.repository.hibernate.StockroomRepositoryImpl;

@Configuration
public class BeanConfig {
	
	@Bean
	public DriverManagerDataSource dataSource() {
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/pbkoffsite");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("1112632");

		return driverManagerDataSource;
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("com.pbkoffsite.web.bean.entity");
		return emfb;
		
	}
	
	
	
	
	//TESTING
	@Bean
	public ItemRepositoryImpl itemRepositoryImpl() {
		return new ItemRepositoryImpl();
	}
	
	@Bean
	public StockroomRepositoryImpl stockroomRepositoryImpl() {
		return new StockroomRepositoryImpl();
	}
	
}
