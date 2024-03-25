package com.thesis.iwantmylawyer.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,String> {

    Optional<City> findByName(String name);

    @Query("select new com.thesis.iwantmylawyer.city.CityResponse(c.id, c.name) from City c where c.id = :id")
    List<CityResponse> findCityResponseById(String id);
}
