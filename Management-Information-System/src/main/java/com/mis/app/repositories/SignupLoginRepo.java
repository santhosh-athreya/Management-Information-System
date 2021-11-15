package com.mis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mis.app.entities.User;

@Repository
public interface SignupLoginRepo extends JpaRepository<User, Integer> {

	boolean existsByUserName(String UserName);
	
	User findByUserName(String UserName);
}
