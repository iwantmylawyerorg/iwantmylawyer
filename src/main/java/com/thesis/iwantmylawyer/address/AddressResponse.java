package com.thesis.iwantmylawyer.address;

public record AddressResponse(
       String id,
       String street,
       String alley,
       String city,
       String state,
       String postalCode,
       String country
) {
}
