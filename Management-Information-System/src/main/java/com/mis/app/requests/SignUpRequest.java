package com.mis.app.requests;

import java.sql.Blob;

public class SignUpRequest {

	
	private String companyName;
	
	private String companySize;
	
	private String industryType;
	
	private String userName; 
	
	private String password;
	
	private Blob companyLogo;
	
	
	public SignUpRequest() {
		
	}


	public SignUpRequest(String companyName, String companySize, String industryType, String userName, String password,
			Blob companyLogo) {
		super();
		this.companyName = companyName;
		this.companySize= companySize;
		this.industryType = industryType;
		this.userName = userName;
		this.password = password;
		this.companyLogo = companyLogo;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanySize() {
		return companyName;
	}


	public void setCompanySize(String companySize) {
		companyName = companySize;
	}


	public String getIndustryType() {
		return industryType;
	}


	public void setIndustryType(String industryType) {
		this.industryType = industryType;
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


	public Blob getCompanyLogo() {
		return companyLogo;
	}


	public void setCompanyLogo(Blob companyLogo) {
		this.companyLogo = companyLogo;
	}


	@Override
	public String toString() {
		return "SignUpRequest [companyName=" + companyName + ", companySize=" + companySize + ", industryType="
				+ industryType + ", userName=" + userName + ", password=" + password + ", companyLogo=" + companyLogo
				+ "]";
	}


	
	 
	
	
}
