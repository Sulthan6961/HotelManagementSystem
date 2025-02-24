package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.AddressPage;
import com.app.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
          private AddressRepository addressRepository;

	 public void saveAddress(AddressPage addressPage) {
	        addressRepository.save(addressPage);
	    }

	public void deleteAddressById(Long id) {
		addressRepository.deleteById(id);
		
	}     
	 public AddressPage updateAddress(Long id, AddressPage addressPage) {
	        Optional<AddressPage> existingAddress = addressRepository.findById(id);

	        if (existingAddress.isPresent()) {
	            AddressPage updatedAddress = existingAddress.get();
	            updatedAddress.setArea(addressPage.getArea());
	            updatedAddress.setCity(addressPage.getCity());
	            updatedAddress.setCountry(addressPage.getCountry());
	            updatedAddress.setFlatHouseNo(addressPage.getFlatHouseNo());
	            updatedAddress.setPincode(addressPage.getPincode());
	            updatedAddress.setState(addressPage.getState());
	            updatedAddress.setStreetAddress(addressPage.getStreetAddress());

	            return addressRepository.save(updatedAddress);
	        } else {
	            return null; 
	        }
	    }
	
    }



