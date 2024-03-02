package com.thesis.iwantmylawyer.like;

import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.post.Post;
import com.thesis.iwantmylawyer.post.PostService;
import com.thesis.iwantmylawyer.postcomment.CreatePostCommentRequest;
import com.thesis.iwantmylawyer.postcomment.PostComment;
import com.thesis.iwantmylawyer.user.User;
import com.thesis.iwantmylawyer.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public void createLike(CreateLikeRequest createLikeRequest){
        User user = userService.findById(createLikeRequest.userId());
        Post post = postService.findById(createLikeRequest.postId());

        Like like = new Like(post, LocalDateTime.now(),user);

        likeRepository.save(like);
    }

    public void deleteLikeById(String likeId){
        likeRepository.delete(findById(likeId));
    }

    public Like findById(String id){
        return likeRepository.findById(id).orElseThrow(() -> new LikeNotFoundException(Constant.LIKE_NOT_FOUND_EXCEPTION));
    }

}
