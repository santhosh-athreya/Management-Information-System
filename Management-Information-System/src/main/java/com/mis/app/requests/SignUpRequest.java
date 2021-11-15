package com.mis.app.requests;

import java.awt.image.BufferedImage;
import java.sql.Blob;

public class SignUpRequest {

	
	private String CompanyName;
	
	private String CompanySize;
	
	private String IndustryType;
	
	private String UserName; 
	
	private String Password;
	
	private Blob CompanyLogo;
	
	
	public SignUpRequest() {
		
	}


	public SignUpRequest(String companyName, String companySize, String industryType, String userName, String password,
			Blob companyLogo) {
		super();
		CompanyName = companyName;
		CompanySize = companySize;
		IndustryType = industryType;
		UserName = userName;
		Password = password;
		CompanyLogo = companyLogo;
	}


	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}


	public String getCompanySize() {
		return CompanySize;
	}


	public void setCompanySize(String companySize) {
		CompanySize = companySize;
	}


	public String getIndustryType() {
		return IndustryType;
	}


	public void setIndustryType(String industryType) {
		IndustryType = industryType;
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


	public Blob getCompanyLogo() {
		return CompanyLogo;
	}


	public void setCompanyLogo(Blob companyLogo) {
		CompanyLogo = companyLogo;
	}


	@Override
	public String toString() {
		return "SignUpRequest [CompanyName=" + CompanyName + ", CompanySize=" + CompanySize + ", IndustryType="
				+ IndustryType + ", UserName=" + UserName + ", Password=" + Password + ", CompanyLogo=" + CompanyLogo
				+ "]";
	}
	 
	
	
}
