package com.pbkoffsite.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
public class AuthUserDetails extends AbstractUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean isEnabled = true;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="user_role",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")}
	)
	private Collection<Role> roles;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoles();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		if(roles == null)
			roles = new ArrayList<>();
		roles.add(role);
	}

	

	@Override
	public String toString() {
		String name = super.getUsername();
		return "CustomUserDetails [username=" + name + ", password=" + password + ", enabled=" + isEnabled + "]";
	}
	
	
}
