package com.thesis.iwantmylawyer.user.client;

import com.thesis.iwantmylawyer.validator.PasswordConstraint;
import com.thesis.iwantmylawyer.validator.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CreateClientRequest(
        @Email(regexp = ".+@.+\\..+",message = "invalid e-mail")
        @NotBlank String email,
        @PasswordConstraint
        @NotBlank String password,
        @NotBlank String passwordMatch,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String telephoneNo
) {
}
