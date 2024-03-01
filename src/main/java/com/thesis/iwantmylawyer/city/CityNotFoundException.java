package com.thesis.iwantmylawyer.city;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException (String message) {
        super(message);
    }
}
