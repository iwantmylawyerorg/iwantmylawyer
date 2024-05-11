package com.thesis.iwantmylawyer.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.stringtemplate.v4.ST;

@Validated
@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<PostResponse>> getAllPosts(@PathVariable @NotNull Integer page,
                                                          @PathVariable @NotNull Integer size){
        return new ResponseEntity<>(postService.getAllPosts(page, size),HttpStatus.OK);
    }
    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostIdResponse> createPost(@RequestBody CreatePostRequest createPostRequest){
        return new ResponseEntity<>(postService.createPost(createPostRequest),HttpStatus.OK);
    }
    @PutMapping(path = "/addPostPhoto/{id}", consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addPostPhoto(@PathVariable @NotBlank String id,
                                             @RequestPart("file") @NotNull MultipartFile file){
        postService.addPostPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
