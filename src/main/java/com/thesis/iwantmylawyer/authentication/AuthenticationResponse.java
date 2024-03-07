package com.thesis.iwantmylawyer.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thesis.iwantmylawyer.user.UserResponse;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        UserResponse userResponse,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken

) {
}
