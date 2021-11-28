package com.mis.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "user_details")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long ID;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String Password;

	@Column(name = "email")
	private String Email;

	@Column(name = "phone_No")
	private String phoneno;

	@Column(name = "designation")
	private String Designation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")

	private Role role;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")

	private Company company;

	@Column(name = "base_location")
	private String BaseLocation;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String designation, Role role, Company company) {
		super();
		this.userName = userName;
		this.Password = password;
		this.Designation = designation;
		this.role = role;
		this.company = company;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPhoneNo() {
		return phoneno;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneno = phoneNo;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		this.Designation = designation;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getBaseLocation() {
		return BaseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		BaseLocation = baseLocation;
	}

}
