package com.thesis.iwantmylawyer.address;

import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public AddressResponse convert(Address from){
        return new AddressResponse(
                from.getId(),
                from.getStreet(),
                from.getAlley(),
                from.getCity(),
                from.getState(),
                from.getPostalCode(),
                from.getCountry()
        );
    }
}
