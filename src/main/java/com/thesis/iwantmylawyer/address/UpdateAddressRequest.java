package com.thesis.iwantmylawyer.address;

public record UpdateAddressRequest(
        String addressId,
        String street,
        String alley,
        String city,
        String state,
        String postalCode,
        String country

) {
}
