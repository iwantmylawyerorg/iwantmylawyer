package com.thesis.iwantmylawyer.user.lawyer;

public record CreateLawyerRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String telephoneNo

) {
}
