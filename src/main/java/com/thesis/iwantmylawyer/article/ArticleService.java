package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.address.AddressNotFoundException;
import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import com.thesis.iwantmylawyer.user.lawyer.LawyerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final LawyerService lawyerService;
    private final ArticleConverter articleConverter;


    public ArticleService(ArticleRepository articleRepository, LawyerService lawyerService, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.lawyerService = lawyerService;
        this.articleConverter = articleConverter;
    }

    public ArticleResponse getById(String articleId){
        return articleConverter.convert(findById(articleId));
    }

    public void createArticle(CreateArticleRequest createArticleRequest){
        Lawyer lawyer = lawyerService.findById(createArticleRequest.lawyerId());
        Article article = new Article(
                createArticleRequest.lawyerId(),
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
    public void deleteById(String articleId){
        articleRepository.delete(findById(articleId));
    }
    public Article findById(String articleId){
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(Constant.ARTICLE_DOES_NOT_FOUND_EXCEPTION));
    }



}
