package com.thesis.iwantmylawyer.city;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityResponseConverter {

    public List<CityResponse> convert(List<City> fromList){
        return fromList.stream().map(city -> new CityResponse(city.getId(), city.getName())).toList();
    }
}
