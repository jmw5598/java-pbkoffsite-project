package com.pbkoffsite.web.bean;

public class UserForm extends AbstractUser{
	
	private String role;
	private String password;
	
	public UserForm() {
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@Override
//	public String toString() {
//		super.toString();
//		return "UserForm [role=" + role + ", password=" + password + "]";
//	}
	
	
	
}
