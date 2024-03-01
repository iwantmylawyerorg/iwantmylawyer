package com.thesis.iwantmylawyer.article;

public record UpdateArticleRequest(
        String articleId,
        String header,
        String text
) {
}
