package com.thesis.iwantmylawyer.user.lawyer;

public record UpdateLawyerRequest(
        String id,
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo
) {
}
