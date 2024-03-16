package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import com.thesis.iwantmylawyer.user.lawyer.LawyerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final LawyerService lawyerService;
    private final ArticleConverter articleConverter;
    private final MinioService minioService;


    public ArticleService(ArticleRepository articleRepository, LawyerService lawyerService, ArticleConverter articleConverter, MinioService minioService) {
        this.articleRepository = articleRepository;
        this.lawyerService = lawyerService;
        this.articleConverter = articleConverter;
        this.minioService = minioService;
    }

    public ArticleResponse getById(String articleId){
        return articleConverter.convert(findById(articleId));
    }

    public void createArticle(CreateArticleRequest createArticleRequest){
        Lawyer lawyer = lawyerService.findById(createArticleRequest.lawyerId());
        Article article = new Article(
                createArticleRequest.header(),
                createArticleRequest.text(),
                LocalDateTime.now(),
                lawyer
        );

        articleRepository.save(article);
    }

    public void updateArticle(UpdateArticleRequest updateArticleRequest){
       Article article = findById(updateArticleRequest.articleId());
       article.setHeader(updateArticleRequest.header().trim().isEmpty() ? article.getHeader() : updateArticleRequest.header());
       article.setText(updateArticleRequest.text().trim().isEmpty() ? article.getText() : updateArticleRequest.text());
       articleRepository.save(article);
    }
    public void uploadPhotoForArticle(String id, MultipartFile file){
        Article article = findById(id);
        article.setPhoto(minioService.uploadImage(file));
        articleRepository.save(article);
    }

    public void deleteById(String articleId){
        articleRepository.delete(findById(articleId));
    }
    public Article findById(String articleId){
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(Constant.ARTICLE_DOES_NOT_FOUND_EXCEPTION));
    }



}
