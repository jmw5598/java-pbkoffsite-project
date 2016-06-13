package com.pbkoffsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.pbkoffsite.web.util.ItemMapper;
import com.pbkoffsite.web.util.SimpleIdDescriptionMapper;
import com.pbkoffsite.web.util.StockroomMapper;
import com.pbkoffsite.web.util.UserMapper;

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
	public JdbcTemplate jdbcTemplate() {
		
		return new JdbcTemplate(dataSource());
		
	}
	
	@Bean
	public ItemMapper itemMapper() {
			
		return new ItemMapper();
		
	}
	
	@Bean
	public StockroomMapper stockroomMapper() {
		
		return new StockroomMapper();
		
	}
	
	@Bean
	public UserMapper userMapper() {
		
		return new UserMapper();
		
	}
	
	@Bean
	public SimpleIdDescriptionMapper simpleIdDescriptionMapper() {
		
		return new SimpleIdDescriptionMapper();
		
	}
	
}
