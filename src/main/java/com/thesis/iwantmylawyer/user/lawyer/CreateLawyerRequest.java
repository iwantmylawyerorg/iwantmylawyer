package com.thesis.iwantmylawyer.user.lawyer;

import jakarta.validation.constraints.NotBlank;

public record CreateLawyerRequest(
        @NotBlank String email,
        @NotBlank  String password,
        @NotBlank String firstName,
        @NotBlank  String lastName,
        @NotBlank String telephoneNo,
        @NotBlank String tcNo,

        @NotBlank String baroSicilNo,

        @NotBlank String cityId


) {
}
