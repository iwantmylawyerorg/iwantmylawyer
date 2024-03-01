package com.thesis.iwantmylawyer.address;

import jakarta.validation.constraints.NotBlank;

public record CreateAddressRequest(
        @NotBlank String street,
        @NotBlank String alley,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String postalCode,
        @NotBlank String country,
        @NotBlank String lawyerId
) {
}
