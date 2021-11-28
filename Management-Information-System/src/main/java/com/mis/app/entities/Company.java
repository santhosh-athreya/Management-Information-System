package com.mis.app.entities;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "company_details")
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int ID;

	@Column(name = "company_name")
	private String CompanyName;

	@Column(name = "industry_type")
	private String IndustryType;

	@Column(name = "company_size")
	private String CompanySize;

	@Column(name = "company_logo")
	private Blob logo;

	@OneToMany(mappedBy = "company")
	private List<User> user;

	public Company() {

	}

	public Company(String companyName, String industryType, String companySize, Blob logo) {
		super();
		CompanyName = companyName;
		IndustryType = industryType;
		CompanySize = companySize;
		this.logo = logo;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getIndustryType() {
		return IndustryType;
	}

	public void setIndustryType(String industryType) {
		IndustryType = industryType;
	}

	public String getCompanySize() {
		return CompanySize;
	}

	public void setCompanySize(String companySize) {
		CompanySize = companySize;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	public int getID() {
		return ID;
	}

}
