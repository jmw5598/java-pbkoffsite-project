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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//UserDetailsService userDetailsService;
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
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
		jdbcImpl.setDataSource(dataSource);
		jdbcImpl.setUsersByUsernameQuery("select username, password, enabled from user where username=?;");
		jdbcImpl.setAuthoritiesByUsernameQuery("select u.username, r.role from user as u inner join user_role as ur on u.id = ur.user_id inner join role as r on ur.role_id = r.id where username=?;");
		
		return jdbcImpl;
		
	}
	
}
