package com.thesis.iwantmylawyer.authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequest(
        String email,

        String password
) {
}
