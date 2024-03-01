package com.thesis.iwantmylawyer.city;

import com.thesis.iwantmylawyer.exception.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityConverter cityConverter;


    public CityService(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }
    public List<CityResponse> getAllCities(){
        return cityConverter.convert(cityRepository.findAll());
    }

    public void createCity(CreateCityRequest createCityRequest){
        if(cityRepository.findByName(createCityRequest.name()).isPresent()){
            throw new CityAlreadyExistsException(Constant.CITY_ALREADY_EXISTS_EXCEPTION);
        }
        City city = new City(createCityRequest.name());
        cityRepository.save(city);
    }
    public City findById(String id){
        return cityRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException(Constant.CITY_DOES_NOT_FOUND_EXCEPTION)
        );
    }
}
