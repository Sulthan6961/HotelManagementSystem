package com.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityid;
    
  
    public Integer getcityid() {
		return cityid;
	}

	public void setcityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityArea() {
		return cityArea;
	}

	public void setCityArea(String cityArea) {
		this.cityArea = cityArea;
	}

	public String getCityPincode() {
		return cityPincode;
	}

	public void setCityPincode(String cityPincode) {
		this.cityPincode = cityPincode;
	}

	public List<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(List<Hotel> hotel) {
		this.hotel = hotel;
	}
	  private String cityName;
	private String cityArea;
    private String cityPincode;
    
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Hotel> hotel;
}
