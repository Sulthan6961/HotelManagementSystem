package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

@Query("SELECT c FROM City c WHERE " +
"(:cityName is NULL OR c.cityName = :cityName) AND " +
"(:cityArea is NULL OR c.cityArea = :cityArea) AND " +
"(:cityPincode is NULL OR c.cityPincode = :cityPincode)")
List< City> searchHotels(@Param("cityName") String cityName,
@Param("cityArea") String cityArea,
@Param("cityPincode") String cityPincode);

@Query(value = "SELECT c.city_id, c.city_area, c.city_name, c.city_pincode FROM city c WHERE c.city_id = :cityId", nativeQuery = true)
List<Object[]> findCityDetailsByCityId(@Param("cityId") Long cityId);



@Query("SELECT c FROM City c " +
	       "JOIN c.hotel h " +
	       "WHERE " +
	       "(c.cityName LIKE CONCAT('%', :destination, '%') OR " +
	       "c.cityArea LIKE CONCAT('%', :destination, '%') OR " +
	       "c.cityPincode LIKE CONCAT('%', :destination, '%') OR " +
	       "h.Hotelname LIKE CONCAT('%', :destination, '%')) AND " +
	       "(:persons IS NULL OR h.persons = :persons)")
	List<City> findByDestination(@Param("destination") String destination, @Param("persons") Integer persons);

boolean existsByCityName(String cityName);

}