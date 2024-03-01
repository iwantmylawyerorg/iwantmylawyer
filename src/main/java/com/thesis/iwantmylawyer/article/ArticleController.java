package com.thesis.iwantmylawyer.article;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable String articleId){
        return new ResponseEntity<>(articleService.getById(articleId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody CreateArticleRequest createArticleRequest){
        articleService.createArticle(createArticleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Void> updateArticle(@RequestBody UpdateArticleRequest updateArticleRequest){
        articleService.updateArticle(updateArticleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String articleId){
        articleService.deleteById(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
