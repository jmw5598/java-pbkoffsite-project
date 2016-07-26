package com.pbkoffsite.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.pbkoffsite.web.service.hibernate.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	UserDetailsService userDetailsService;
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
			.antMatchers("/**").access("hasRole('ROLE_READ') OR hasRole('ROLE_WRITE') OR hasRole('ROLE_ADMIN')")
			.antMatchers("/inventory/item/add").access("hasRole('ROLE_WRITE') OR hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/home")
			.failureUrl("/login?error=true")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/")
			.permitAll()
			.and()
		.csrf();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web
			.ignoring()
				.antMatchers("/resources/**", "/login?language=en", "/login?language=es");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsServiceImpl) throws Exception {
		
		auth
			.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsServiceImpl userDetailsServiceImpl() {
		return new UserDetailsServiceImpl();
	}
	
}
