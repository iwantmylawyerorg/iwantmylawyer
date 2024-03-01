package com.thesis.iwantmylawyer.article;

import jakarta.validation.constraints.NotBlank;

public record UpdateArticleRequest(
        @NotBlank String articleId,
        String header,
        String text
) {
}
