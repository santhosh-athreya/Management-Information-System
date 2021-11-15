package com.mis.app.requests;

public class LoginRequest {
	
	
	private String UserName;
	
	private String Password;
	
	
	public LoginRequest() {
		
	}


	public LoginRequest(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}


	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	@Override
	public String toString() {
		return "LoginRequest [UserName=" + UserName + ", Password=" + Password + "]";
	}

	
}
