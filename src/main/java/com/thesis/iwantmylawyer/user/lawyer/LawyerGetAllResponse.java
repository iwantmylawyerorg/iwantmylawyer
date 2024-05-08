package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.city.CityResponse;

public record LawyerGetAllResponse(
        String id,
        String firstName,
        String lastName,
        String telephoneNo,
        String lawyerPhoto,
        CityResponse  baroKayitIl
) {
}
