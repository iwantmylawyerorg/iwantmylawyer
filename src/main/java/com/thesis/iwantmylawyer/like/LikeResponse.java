package com.thesis.iwantmylawyer.like;
import com.thesis.iwantmylawyer.user.UserResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record LikeResponse(
        String id,
        LocalDateTime localDateTime,
        UserResponse userResponse
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
