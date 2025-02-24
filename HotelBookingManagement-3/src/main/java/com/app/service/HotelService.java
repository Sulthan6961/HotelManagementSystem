package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Hotel;
import com.app.repository.HotelRepository;

@Service
public class HotelService {
@Autowired
private HotelRepository hotelRepository;

public List< Hotel> findHotelsByPersons(Integer persons) {
return hotelRepository.findByPersons(persons);
}

public List<Hotel> findHotelsByPersonsAndAc(Integer persons, Boolean isAc) {

	return hotelRepository.findHotelsByPersonsAndIsAc(persons,isAc);
}

public List< Hotel> findHotelsByCityAndPersons(String cityName, Integer persons) {
return hotelRepository.findHotelsByCityAndPersons(cityName, persons);
}

public List<Hotel> findHotelsByCityAndPersonsAndAc(String cityName, Integer persons, Boolean Ac) {
	// TODO Auto-generated method stub
	return hotelRepository.findByHotelnameAndPersonsAndIsAc(cityName, persons, Ac);
}

public List<Hotel> findByNameAndPersonsAndAc(String cityName, Integer persons, Boolean ac) {
	// TODO Auto-generated method stub
	return  hotelRepository.findByHotelnameAndPersonsAndIsAc(cityName, persons,ac);
}
}

