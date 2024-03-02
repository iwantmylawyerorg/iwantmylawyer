package com.thesis.iwantmylawyer.postcomment;

public record CreatePostCommentRequest(
        String text,
        String postId,
        String userId

) {
}
