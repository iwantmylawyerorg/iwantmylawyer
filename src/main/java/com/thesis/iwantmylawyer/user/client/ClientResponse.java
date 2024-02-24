package com.thesis.iwantmylawyer.user.client;

public record ClientResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        String telephoneNo
) {
}
