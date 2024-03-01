package com.thesis.iwantmylawyer.address;

public record CreateAddressRequest(
        String street,
        String alley,
        String city,
        String state,
        String postalCode,
        String country,
        String lawyerId
) {
}
