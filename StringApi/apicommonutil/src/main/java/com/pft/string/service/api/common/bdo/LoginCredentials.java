package com.pft.string.service.api.common.bdo;


public class LoginCredentials implements java.io.Serializable {

	private String username;
	private String password;

	public LoginCredentials() {
	}

	public LoginCredentials(String username, String password) {
		this.username=username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
