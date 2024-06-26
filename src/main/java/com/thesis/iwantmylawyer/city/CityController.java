package com.thesis.iwantmylawyer.city;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@Validated
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCity(@RequestBody CreateCityRequest createCityRequest){
        cityService.createCity(createCityRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
