package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.validator.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateLawyerRequest(
        @Email(regexp = ".+@.+\\..+",message = "invalid e-mail")
        @NotBlank String email,
        @PasswordConstraint
        @NotBlank  String password,
        @NotBlank String firstName,
        @NotBlank  String lastName,
        @NotBlank String telephoneNo,
        @NotBlank String tcNo,
        @NotBlank String baroSicilNo,

        @NotBlank String cityId


) {
}
