package com.thesis.iwantmylawyer.user.lawyer;

import jakarta.validation.constraints.NotBlank;

public record UpdateLawyerRequest(
        @NotBlank String id,
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo
) {
}
