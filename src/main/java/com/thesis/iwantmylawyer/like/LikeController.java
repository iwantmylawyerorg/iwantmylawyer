package com.thesis.iwantmylawyer.like;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Void> createLike(@RequestBody CreateLikeRequest createLikeRequest){
        likeService.createLike(createLikeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<Void> deleteLike(@PathVariable String postId,@PathVariable String userId){
        likeService.deleteLikeById(postId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
