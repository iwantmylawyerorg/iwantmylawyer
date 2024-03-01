package com.thesis.iwantmylawyer.city;

import jakarta.validation.constraints.NotBlank;

public record CreateCityRequest(
        @NotBlank String name
) {
}
