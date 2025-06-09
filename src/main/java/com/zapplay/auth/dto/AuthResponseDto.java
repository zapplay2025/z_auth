package com.zapplay.auth.dto;

import com.zapplay.auth.model.User;

public class AuthResponseDto {
    private String token;
    private User user;
    private String message;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", user=" + user + ", message=" + message + "]";
	}


    
}
