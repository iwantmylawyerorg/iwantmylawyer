package com.thesis.iwantmylawyer.article;

import jakarta.validation.constraints.NotBlank;

public record CreateArticleRequest(
        @NotBlank String lawyerId,
        @NotBlank String header,
        @NotBlank String text
) {
}
