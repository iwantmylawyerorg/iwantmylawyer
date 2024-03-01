package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.city.CityResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleConverter {


    public List<ArticleResponse> convert(List<Article> fromList){
        return fromList.stream().map(article -> new ArticleResponse(
                article.getId(),
                article.getHeader(),
                article.getText(),
                article.getLocalDateTime())).toList();

    }

    public ArticleResponse convert(Article from){
        return new ArticleResponse(
                from.getId(),
                from.getHeader(),
                from.getText(),
                from.getLocalDateTime()
        );
    }
}
