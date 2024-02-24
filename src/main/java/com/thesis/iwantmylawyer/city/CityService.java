package com.thesis.iwantmylawyer.city;

import com.thesis.iwantmylawyer.exception.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityResponseConverter cityResponseConverter;


    public CityService(CityRepository cityRepository, CityResponseConverter cityResponseConverter) {
        this.cityRepository = cityRepository;
        this.cityResponseConverter = cityResponseConverter;
    }
    public List<CityResponse> getAllCities(){
        return cityResponseConverter.convert(cityRepository.findAll());
    }

    public void createCity(CreateCityRequest createCityRequest){
        if(cityRepository.findByName(createCityRequest.name()).isPresent()){
            throw new CityAlreadyExistsException(Constant.CITY_ALREADY_EXISTS_EXCEPTION);
        }
        City city = new City(createCityRequest.name());
        cityRepository.save(city);
    }
}
