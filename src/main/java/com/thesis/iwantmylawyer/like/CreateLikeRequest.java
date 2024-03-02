package com.thesis.iwantmylawyer.like;

public record CreateLikeRequest(
        String postId,
        String userId
) {
}
