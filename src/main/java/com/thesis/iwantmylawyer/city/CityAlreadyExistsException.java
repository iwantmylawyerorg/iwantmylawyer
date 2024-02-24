package com.thesis.iwantmylawyer.city;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException (String message) {
        super(message);
    }
}
