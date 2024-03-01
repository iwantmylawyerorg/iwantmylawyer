package com.thesis.iwantmylawyer.city;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityConverter {

    public List<CityResponse> convert(List<City> fromList){
        return fromList.stream().map(city -> new CityResponse(city.getId(), city.getName())).toList();
    }
    public CityResponse convert(City from){
        return new CityResponse(
                from.getId(),
                from.getName()
        );
    }
}
