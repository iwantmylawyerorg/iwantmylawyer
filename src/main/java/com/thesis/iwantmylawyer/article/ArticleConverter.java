package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.city.CityResponse;
import com.thesis.iwantmylawyer.minio.MinioService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleConverter {

    private final MinioService minioService;

    public ArticleConverter(MinioService minioService) {
        this.minioService = minioService;
    }

    public List<ArticleResponse> convert(List<Article> fromList){
        return fromList.stream().map(article -> new ArticleResponse(
                article.getId(),
                article.getHeader(),
                article.getText(),
                article.getLocalDateTime(),
                minioService.getBase64Image(article.getPhoto()))).toList();

    }

    public ArticleResponse convert(Article from){
        return new ArticleResponse(
                from.getId(),
                from.getHeader(),
                from.getText(),
                from.getLocalDateTime(),
                minioService.getBase64Image(from.getPhoto())
        );
    }
}
