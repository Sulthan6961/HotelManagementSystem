
package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.DuplicateException;
import com.app.Exception.ResourceNotFoundException;
import com.app.entity.City;
import com.app.entity.Hotel;
import com.app.repository.CityRepository;
import com.app.repository.HotelRepository;
import com.app.service.CityService;
import com.app.service.HotelService;

@RestController
@RequestMapping("/city")
@CrossOrigin("origins=*")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HotelService hotelService;
	@Autowired
	private CityService cityService;

	@GetMapping("/search-by-persons")
	public List<Hotel> getHotelsByPersons(@RequestParam Integer persons) {
		return hotelService.findHotelsByPersons(persons);
	}

	@GetMapping("/search")
	public ResponseEntity<List<City>> searchHotels(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) String area, @RequestParam(required = false) String pincode) {
		System.out.println("search method");

		if (cityName == null && area == null && pincode == null) {
			return ResponseEntity.badRequest().build();
		}

		List<City> cities = cityRepository.searchHotels(cityName, area, pincode);
		if (cities.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(cities);
	}

	@GetMapping("/searchforAc")
	public List<Hotel> getHotelsByPersonsAndAc(@RequestParam Integer persons, @RequestParam Boolean isAc) {
		return hotelService.findHotelsByPersonsAndAc(persons, isAc);
	}

	@PostMapping("/add")
	public ResponseEntity<City> addCityWithHotels(@RequestBody City city) {
		if (cityRepository.existsByCityName(city.getCityName())) {
			throw new DuplicateException("City with name '" + city.getCityName() + "' already exists.");
			}
			cityRepository.save(city);


			for (Hotel hotel : city.getHotel()) {
			hotel.setCity(city);
			hotelRepository.save(hotel);
			}

			return ResponseEntity.ok(city);
			}

	@GetMapping("/searchByCityAndPersons")
	public List<Hotel> getHotelsByCityAndPersons(@RequestParam(required = false) String cityName,
			@RequestParam(required = false) Integer persons) {
		return hotelService.findHotelsByCityAndPersons(cityName, persons);
	}

	@GetMapping("/searchByCityAndPersonsAndAc")
	public List<Hotel> getHotelsByCityAndPersonsAndAc(
			@RequestParam(required = false) String cityName,
			@RequestParam(required = false) Integer persons,
			@RequestParam(required = false) Boolean Ac) {
		return hotelService.findByNameAndPersonsAndAc(cityName, persons, Ac);
	}

	@GetMapping("/searching")
	public ResponseEntity< List< City>> searchCities(@RequestParam String destination, @RequestParam Integer persons) {
	System.out.println("Controller method invoked with destination: " + destination);
	List< City> cities = cityService.searchCities(destination,persons);
	if (cities.isEmpty()) {
	throw new ResourceNotFoundException("No Data found matching the specified criteria.");
	}

	return ResponseEntity.ok(cities);

	}
	
	
}
