package com.thesis.iwantmylawyer.user.client;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String telephoneNo
) {
}
