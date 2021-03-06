package com.pbkoffsite.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pbkoffsite.web.repository.ItemRepositoryImpl;
import com.pbkoffsite.web.repository.StockroomRepositoryImpl;
import com.pbkoffsite.web.repository.UserRepositoryImpl;

@Configuration
public class BeanConfig {
	
	// use apache commons DBCP's BasicDatasource
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
		emfb.setPackagesToScan("com.pbkoffsite.web.bean");
		return emfb;
		
	}
	
	
	
	
	//TESTING
//	@Bean
//	public ItemRepositoryImpl itemRepositoryImpl() {
//		return new ItemRepositoryImpl();
//	}
//	
//	@Bean
//	public StockroomRepositoryImpl stockroomRepositoryImpl() {
//		return new StockroomRepositoryImpl();
//	}
//	
//	@Bean
//	public UserRepositoryImpl userRepositoryImpl() {
//		return new UserRepositoryImpl();
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
