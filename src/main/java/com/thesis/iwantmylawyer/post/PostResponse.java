package com.thesis.iwantmylawyer.post;

import com.thesis.iwantmylawyer.like.LikeResponse;
import com.thesis.iwantmylawyer.postcomment.PostCommentResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        String id,
        String text,
        String postPhoto,
        LocalDateTime localDateTime,
        List<PostCommentResponse> postCommentResponseList,
        List<LikeResponse> likeResponseList
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
