package com.mis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.app.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
