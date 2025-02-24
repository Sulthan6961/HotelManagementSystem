package com.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Exception.ResourceNotFoundException;
import com.app.entity.Signup;
import com.app.repository.SignupRepository;

@Service
public class SignInService {

	
	 @Autowired
	    private SignupRepository signUpRepository;

	    private final Map<String, String> otpStorage = new HashMap<>();

	    // Generate and send OTP
	    public String generateAndSendOtp(String mobileNo) {
	        Signup user = signUpRepository.findBymobileNumber(mobileNo)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with mobile number: " + mobileNo));

	        String otp = generateOtp();
	        otpStorage.put(mobileNo, otp);

	        // Here, integrate with an SMS gateway to send the OTP to the user's mobile number
	        System.out.println("OTP sent to " + mobileNo + ": " + otp); // Placeholder for actual SMS sending logic

	        return "OTP has been sent to the registered mobile number.";
	    }

	    // Validate the OTP
	    public boolean validateOtp(String mobileNo, String otp) {
	        if (!otpStorage.containsKey(mobileNo)) {
	            throw new IllegalArgumentException("No OTP generated for this mobile number.");
	        }

	        String validOtp = otpStorage.get(mobileNo);
	        if (validOtp.equals(otp)) {
	            otpStorage.remove(mobileNo); // Invalidate OTP after use
	            return true;
	        }
	        return false;
	    }

	    // Generate a random 6-digit OTP
	    private String generateOtp() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000);
	        return String.valueOf(otp);
	    }
}
