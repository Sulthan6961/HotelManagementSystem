package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByPersons(Integer persons);

    @Query("""
            SELECT h
            FROM Hotel h
            JOIN h.city c
            WHERE (:cityName IS NULL OR LOWER(c.cityName) = LOWER(:cityName)) AND
            (:persons IS NULL OR h.persons >= :persons)
            """)
    List<Hotel> findHotelsByCityAndPersons(@Param("cityName") String cityName, @Param("persons") Integer persons);

    // Corrected query below:
    @Query("SELECT h FROM Hotel h WHERE " +
           "(:hotelname IS NULL OR h.Hotelname =: hotelname) AND " + 
           "(:persons IS NULL OR h.persons >= :persons) AND " +
           "(:isAc IS NULL OR h.isAc = :isAc)")
    List<Hotel> findByHotelnameAndPersonsAndIsAc(@Param("hotelname") String hotelname, 
                                                 @Param("persons") Integer persons,
                                                 @Param("isAc") Boolean isAc);

    List<Hotel> findHotelsByPersonsAndIsAc(Integer persons, Boolean isAc);
}


	
	



