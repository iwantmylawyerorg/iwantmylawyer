package com.thesis.iwantmylawyer.user.client;

public record UpdateClientRequest(
        String id,
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo
) {

}
