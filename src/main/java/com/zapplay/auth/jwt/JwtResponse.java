package com.zapplay.auth.jwt;

public class JwtResponse {
	
	

	public JwtResponse(String token, String userName) {
		super();
		this.token = token;
		this.userName = userName;
	}

	private String token;

	private String userName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", userName=" + userName + "]";
	}



	public JwtResponse() {
		super();
	}

	public static class Builder {

		private String token;
		private String userName;

		public Builder token(String token) {
			this.token = token;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public JwtResponse build() {

			return new JwtResponse(token, userName);
		}

	}

	public static Builder builder() {

		return new Builder();

	}

}
