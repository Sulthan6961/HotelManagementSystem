package com.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Signup;

public interface SignupRepository extends JpaRepository<Signup, Long> {

	Optional<Signup> findByEmail(String email);
	
	
	Optional<Signup> findBymobileNumber(String mobileNumber);


	Optional<Signup> findById(Long id);


	List<Signup> findSignupsByFullNameOrDobOrEmailOrMobileNumber(String fullName, LocalDate dob, String email,
			String mobileNumber);

	Optional<Signup> findByMobileNumber(String mobileNumber);


	static Optional<Signup> findByMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}  
	}
   
	

