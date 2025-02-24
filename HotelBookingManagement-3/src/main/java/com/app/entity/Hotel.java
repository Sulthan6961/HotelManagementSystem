package com.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Hotel {
    
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;
    private String Hotelname;
	private String rating;
    private String description;
    private String address;
    private Integer persons;
	private String amountPerNight;
    private String imageUrl;
    private Boolean isAc;
    
    public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

   
	 
	    public String getHotelname() {
			return Hotelname;
		}

		public void setHotelname(String hotelname) {
			Hotelname = hotelname;
		}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public String getAmountPerNight() {
		return amountPerNight;
	}

	public void setAmountPerNight(String amountPerNight) {
		this.amountPerNight = amountPerNight;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsAc() {
		return isAc;
	}

	public void setIsAc(Boolean isAc) {
		this.isAc = isAc;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;  // Removed the extra whitespace here
}
