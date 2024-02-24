package com.thesis.iwantmylawyer.user.client;

public record CreateClientRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo
) {
}
