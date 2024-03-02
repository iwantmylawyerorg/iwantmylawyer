package com.thesis.iwantmylawyer.like;

import com.thesis.iwantmylawyer.user.UserResponse;

import java.time.LocalDateTime;

public record LikeResponse(
        String id,
        LocalDateTime localDateTime,
        UserResponse userResponse
) {
}
