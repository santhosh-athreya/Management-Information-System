package com.mis.app.responses;

public class LoginResponse {
    
	
	private String userName;
    private String email;
    private String accessToken;

    public LoginResponse() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    public LoginResponse(String userName, String email, String accessToken) {
		super();
		this.userName = userName;
		this.email = email;
		this.accessToken = accessToken;
	}



	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }



	@Override
	public String toString() {
		return "LoginResponse [userName=" + userName + ", email=" + email + ", accessToken=" + accessToken + "]";
	}
    
    
}
