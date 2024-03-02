package com.thesis.iwantmylawyer.postcomment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/postComment")
public class PostCommentController {
    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }
    @PostMapping
    public ResponseEntity<Void> createPostComment(@RequestBody CreatePostCommentRequest createPostCommentRequest){
        postCommentService.createPostComment(createPostCommentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostComment(@PathVariable String id){
        postCommentService.deletePostCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
