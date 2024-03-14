package com.thesis.iwantmylawyer.authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequest(
        @NotBlank String email,

        @NotBlank String password
) {
}
