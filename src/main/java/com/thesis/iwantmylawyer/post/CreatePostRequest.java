package com.thesis.iwantmylawyer.post;

public record CreatePostRequest(
        String lawyerId,
        String text

) {
}
