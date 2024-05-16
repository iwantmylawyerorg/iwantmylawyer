package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.address.AddressResponse;
import com.thesis.iwantmylawyer.city.CityResponse;

public record UnconfirmedLawyerResponse(
        String firstName,
        String lastName,
        String baroSicilNo,
        String tcNo,
        String lawyerPhoto,
        String avukatKartPhoto,
        String tcPhoto,
        AddressResponse addressResponse
) {
}
