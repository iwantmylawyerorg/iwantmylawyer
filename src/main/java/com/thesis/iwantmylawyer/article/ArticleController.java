package com.thesis.iwantmylawyer.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
@Validated
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable @NotBlank String articleId){
        return new ResponseEntity<>(articleService.getById(articleId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleResponseWithId> createArticle(@RequestBody CreateArticleRequest createArticleRequest){
        return new ResponseEntity<>(articleService.createArticle(createArticleRequest),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Void> updateArticle(@RequestBody UpdateArticleRequest updateArticleRequest){
        articleService.updateArticle(updateArticleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Void> uploadPhotoForArticle(@PathVariable @NotBlank String articleId, @RequestPart("file") @NotNull MultipartFile file){
        articleService.uploadPhotoForArticle(articleId, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable @NotBlank String articleId){
        articleService.deleteById(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
