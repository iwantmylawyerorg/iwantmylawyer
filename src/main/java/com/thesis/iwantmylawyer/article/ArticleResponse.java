package com.thesis.iwantmylawyer.article;

import java.time.LocalDateTime;

public record ArticleResponse(
        String id,
        String header,
        String text,
        LocalDateTime localDateTime,

        String photo
) {
}
