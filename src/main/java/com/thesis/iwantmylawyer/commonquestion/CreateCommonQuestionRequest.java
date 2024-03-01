package com.thesis.iwantmylawyer.commonquestion;

import jakarta.validation.constraints.NotBlank;

public record CreateCommonQuestionRequest(
        @NotBlank String questionLine,
        @NotBlank String answerLine,
        @NotBlank String lawyerId
) {
}
