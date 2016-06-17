package com.pbkoffsite.web.bean;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
	
	String role;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}
