package com.zapplay.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;

public class JwtRequest {
    
    private String userName;
    private String password;
	public JwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public JwtRequest() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JwtRequest [password=" + password + ", userName=" + userName + "]";
	}

  
    
  
}
