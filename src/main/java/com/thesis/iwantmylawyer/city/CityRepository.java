package com.thesis.iwantmylawyer.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,String> {

    Optional<City> findByName(String name);
}
