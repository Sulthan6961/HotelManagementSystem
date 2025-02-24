package com.app.service;

import java.util.Collections; // To return an empty list if no results
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Login;
import com.app.repository.LoginRepository;

@Service
public class LoginService {

    private static LoginRepository loginRepository = null;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        LoginService.loginRepository = loginRepository;
    }

        public static List<Login> searchLogins(String mobileNumber, String password) {
        if (mobileNumber != null && password != null) {
            return loginRepository.findByMobileNumberAndPassword(mobileNumber, password); // Method to find by both
        } else if (mobileNumber != null) {
            return (List<Login>) loginRepository.findByMobileNumber(mobileNumber); // Method to find by mobile number
        } else if (password != null) {
            return loginRepository.findByPassword(password);
        } else {
            return Collections.emptyList();  
        }
    }

    
    public String registerUser(String mobileNumber, String password) {
        if (loginRepository.findByMobileNumber(mobileNumber) != null) {
            return "Mobile number already registered!";
        }

        Login login = new Login();
        login.setMobileNumber(mobileNumber);
        login.setPassword(password);
        
        loginRepository.save(login);

        return "User registered successfully!";
    }

	public Login getUserByMobileNumber(String mobileNumber) {
		
		return null;
	}

}
