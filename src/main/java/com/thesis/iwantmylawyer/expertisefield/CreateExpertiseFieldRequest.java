package com.thesis.iwantmylawyer.expertisefield;

import jakarta.validation.constraints.NotBlank;

public record CreateExpertiseFieldRequest(
        @NotBlank String name,
        @NotBlank String description
) {
}
