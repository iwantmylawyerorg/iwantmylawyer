package com.thesis.iwantmylawyer.authentication;

public record AuthenticationRequest(
        String email,
        String password
) {
}
