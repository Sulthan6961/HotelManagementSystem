package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.SignupDto.SignupDto;
import com.app.entity.Signup;
import com.app.service.SignupService;

@RestController
@RequestMapping("/Signup")
public class SignupController {

	@Autowired
	private SignupService SignupService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Signup>> searchSignups(
	    @RequestParam(required = false) String fullName,
	    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
	    @RequestParam(required = false) String email,
	    @RequestParam(required = false) String mobileNumber
	) {
	    List<Signup> signups = SignupService.searchSignups(fullName, dob, email, mobileNumber);
	    if (!signups.isEmpty()) {
	        return ResponseEntity.ok(signups);
	    } else {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(signups);
	    }
	}
	
	@PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody SignupDto signupDto) {
        return SignupService.registerUser(signupDto);
    }
	
	 @PutMapping("/{id}")
	    public String updateUser(@PathVariable Long id, @RequestBody Signup signup) {
	        return SignupService.updateUser(id, signup);
	    }
	 @DeleteMapping("/{id}")
	   public String deleteUser(@PathVariable Long id) {
		  return SignupService.deleteUser(id);
		 
	 }
	
	}

	


