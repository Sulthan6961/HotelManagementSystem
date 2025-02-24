package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
      Login findByMobileNumber(String mobileNumber);

	
	List<Login> findByMobileNumberAndPassword(String mobileNumber, String password);

	List<Login> findByPassword(String password);
}