package com.thesis.iwantmylawyer.like;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.post.Post;
import com.thesis.iwantmylawyer.post.PostService;
import com.thesis.iwantmylawyer.user.User;
import com.thesis.iwantmylawyer.user.UserService;
import org.springframework.cache.annotation.CacheEvict;
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

    @CacheEvict(value = Constant.REDIS_POST_CLASS, allEntries = true, condition = "#createLikeRequest != null")
    public void createLike(CreateLikeRequest createLikeRequest){
        User user = userService.findById(createLikeRequest.userId());
        Post post = postService.findById(createLikeRequest.postId());

        Like like = new Like(post, LocalDateTime.now(),user);

        likeRepository.save(like);
    }

    @CacheEvict(value = Constant.REDIS_POST_CLASS, allEntries = true, condition = "#postId != null && #userId != null")
    public void deleteLikeById(String postId,String userId){
        likeRepository.delete(findByUserIdAndPostId(postId,userId));
    }

    public Like findById(String id){
        return likeRepository.findById(id).orElseThrow(() -> new LikeNotFoundException(Constant.LIKE_NOT_FOUND_EXCEPTION));
    }
    public Like findByUserIdAndPostId(String postId,String userId){
        return likeRepository.findByPost_IdAndUser_Id(postId,userId).orElseThrow(() -> new LikeNotFoundException(Constant.LIKE_NOT_FOUND_EXCEPTION));
    }

}
