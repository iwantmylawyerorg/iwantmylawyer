package com.thesis.iwantmylawyer.user;


import com.thesis.iwantmylawyer.like.LikeResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record UserResponse(
        String id,
        String email,
        String firstName,
        String lastName,
        Role role

)implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
