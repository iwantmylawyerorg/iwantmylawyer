package com.thesis.iwantmylawyer.article;

public record CreateArticleRequest(
        String lawyerId,
        String header,
        String text
) {
}
