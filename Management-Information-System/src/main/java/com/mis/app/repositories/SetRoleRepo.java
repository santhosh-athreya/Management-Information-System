package com.mis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mis.app.entities.Role;

@Repository
public interface SetRoleRepo extends JpaRepository<Role, Integer> {
	
	Role findByRoleType(String RoleType);

}
