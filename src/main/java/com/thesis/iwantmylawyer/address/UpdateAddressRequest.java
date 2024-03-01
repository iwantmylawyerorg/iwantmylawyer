package com.thesis.iwantmylawyer.address;

import jakarta.validation.constraints.NotBlank;

public record UpdateAddressRequest(
        @NotBlank String lawyerId,
        String street,
        String alley,
        String city,
        String state,
        String postalCode,
        String country

) {
}
