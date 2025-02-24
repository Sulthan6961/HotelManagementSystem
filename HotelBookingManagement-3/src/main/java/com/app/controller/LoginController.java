
package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Login;
import com.app.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/search")
    public ResponseEntity<List<Login>> searchLogins(
        @RequestParam(required = false) String mobileNumber,
        @RequestParam(required = false) String password 
    ) {
        List<Login> logins = LoginService.searchLogins(mobileNumber, password); 
        if (logins != null && !logins.isEmpty()) {
            return ResponseEntity.ok(logins); 
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(logins); 
        }
    } 
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Login login) {
        String response = loginService.registerUser(login.getMobileNumber(), login.getPassword());
        return ResponseEntity.ok(response);
    }

}
