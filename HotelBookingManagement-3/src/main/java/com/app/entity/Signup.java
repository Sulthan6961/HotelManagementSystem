package com.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Signup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String fullName;
    private LocalDate dob;
    private String email;
	private String mobileNumber;
    
    public String getFullName() {
		return fullName;
	}
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getmobileNumber() {
		return mobileNumber;
	}
	public void setmobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Signup(Integer id, String fullName, LocalDate dob, String email, String mobileNumber) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dob = dob;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public Signup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
