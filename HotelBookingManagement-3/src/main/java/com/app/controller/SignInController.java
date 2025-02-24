package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.SignInService;

@RestController
@RequestMapping("/api/signin")
public class SignInController {
	
	 @Autowired
	    private SignInService signInService;

	    // Endpoint to generate and send OTP
	    @PostMapping("/sendOtp")
	    public ResponseEntity<String> sendOtp(@RequestParam String mobileNumber) {
	        String response = signInService.generateAndSendOtp(mobileNumber);
	        return ResponseEntity.ok(response);
	    }

	    // Endpoint to validate OTP and sign in
	    @PostMapping("/verifyOtp")
	    public ResponseEntity<String> verifyOtp(@RequestParam String mobileNumber, @RequestParam String otp) {
	        boolean isValid = signInService.validateOtp(mobileNumber, otp);

	        if (isValid) {
	            return ResponseEntity.ok("Sign in successful!");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP. Please try again.");
	        }
	    }

}