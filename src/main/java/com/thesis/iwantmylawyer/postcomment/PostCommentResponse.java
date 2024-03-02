package com.thesis.iwantmylawyer.postcomment;

import com.thesis.iwantmylawyer.user.UserResponse;

import java.time.LocalDateTime;

public record PostCommentResponse(
        String postCommentId,
        String text,
        LocalDateTime localDateTime,
        UserResponse userResponse
) {
}
