package com.springwebproject.dao;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import com.springwebproject.annotations.ValidEmail;


public class User {
	@NotBlank
	@Size(min=5,max=15)
	private String username;
	@ValidEmail
	private String email;
	@NotBlank
	@Size(min=4,max=15)
	private String password;
	private boolean enabled = false;
	private String authority;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public User(String username, String email, String password,
			boolean enabled, String authority) {
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
	}
	
	public User(){
		
	}
	
	

}
