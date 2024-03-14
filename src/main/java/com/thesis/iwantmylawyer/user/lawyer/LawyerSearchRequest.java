package com.thesis.iwantmylawyer.user.lawyer;

public record LawyerSearchRequest(
        Integer pageNumber,
        Integer pageSize,
        String firstName,
        String lastName,
        String city

) {
}
