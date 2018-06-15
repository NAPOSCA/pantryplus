package org.wecancodeit.pantryplus.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserLogin {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	
	private String password;
	
	public UserLogin() {}

	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
