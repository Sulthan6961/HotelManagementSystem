package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.City;
import com.app.repository.CityRepository;

@Service
public class CityService {

	@Autowired
		private CityRepository cityRepository;
	
	
		public List< City> searchCities(String destination, Integer persons) {
			return cityRepository.findByDestination(destination, persons);
			}

	

}
