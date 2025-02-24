package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.SignupDto.SignupDto;
import com.app.entity.Signup;
import com.app.repository.SignupRepository;

@Service
public class SignupService {

    @Autowired
    private final SignupRepository signupRepository;

    public SignupService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    
    public Signup getSignupById(Long id) {
        return signupRepository.findById(id).orElse(null);
    }

    public List<Signup> searchSignups(String fullName, LocalDate dob, String email, String mobileNumber) {
    	
        return signupRepository.findSignupsByFullNameOrDobOrEmailOrMobileNumber(fullName, dob, email, mobileNumber);
    }
    
    public ResponseEntity<String> registerUser(SignupDto signupDto) {
        Optional<Signup> existingMobileNumber = signupRepository.findBymobileNumber(signupDto.getMobileNumber());
        if (existingMobileNumber.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number is already registered.");
        }

        
        Optional<Signup> existingEmail = signupRepository.findByEmail(signupDto.getEmail());
        if (existingEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered.");
        }
        Signup newSignup = new Signup();
        newSignup.setFullName(signupDto.getFullName());
        newSignup.setDob(signupDto.getDob());
        newSignup.setEmail(signupDto.getEmail());
        newSignup.setmobileNumber(signupDto.getMobileNumber());

        signupRepository.save(newSignup);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

	public boolean isEmailExist(String email) {
		return false;
	}
	
	 public String updateUser(Long id, Signup signup) {
	        
	        if (signupRepository.existsById(id)) {
	            
	            Signup existingSignup = signupRepository.findById(id).orElse(null);
	            if (existingSignup != null) {
	                // Update fields
	                existingSignup.setFullName(signup.getFullName());
	                existingSignup.setDob(signup.getDob());
	                existingSignup.setEmail(signup.getEmail());
	                existingSignup.setmobileNumber(signup.getmobileNumber());

	                signupRepository.save(existingSignup);
	                return "User updated successfully";
	            }
	        } else {
	            return "User with ID " + id + " not found";
	        }

	        return "Error updating user";
	 }

	 
	    public String deleteUser(Long id) {
	      
	        if (signupRepository.existsById(id)) {
	            
	            signupRepository.deleteById(id);
	            return "User with ID " + id + " deleted successfully";
	        } else {
	            return "User with ID " + id + " not found";
	        }
	    }
	public String DeleteUser(Long id) {
		return null;
	}
}

