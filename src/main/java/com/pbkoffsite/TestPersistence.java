package com.pbkoffsite;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pbkoffsite.config.BeanConfig;

public class TestPersistence {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(BeanConfig.class);
		
		PasswordEncoder passwordEncoder = context.getBean("passwordEncoder", PasswordEncoder.class);
	
		System.out.println(passwordEncoder.encode("Maggie091685"));
	}

}
