package com.thesis.iwantmylawyer.user.client;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientRequest(
        @NotBlank String id,
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo
) {

}
