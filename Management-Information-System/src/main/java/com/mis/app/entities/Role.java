package com.mis.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "role")
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "role_type")
	private String roleType;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(String roleType) {
		super();
		this.roleType = roleType;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return roleType;
	}

	@OneToMany(mappedBy = "role")
	private List<User> users;

	@Override
	public String toString() {
		return "Role [id=" + id + ", Role=" + roleType + "]";
	}

}
