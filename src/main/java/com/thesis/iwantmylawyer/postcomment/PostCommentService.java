package com.thesis.iwantmylawyer.postcomment;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.post.Post;
import com.thesis.iwantmylawyer.post.PostService;
import com.thesis.iwantmylawyer.user.User;
import com.thesis.iwantmylawyer.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final UserService userService;
    private final PostService postService;

    public PostCommentService(PostCommentRepository postCommentRepository, UserService userService, PostService postService) {
        this.postCommentRepository = postCommentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public void createPostComment(CreatePostCommentRequest createPostCommentRequest){
        User user = userService.findById(createPostCommentRequest.userId());
        Post post = postService.findById(createPostCommentRequest.postId());

        PostComment postComment = new PostComment(createPostCommentRequest.text(), LocalDateTime.now(),post,user);

        postCommentRepository.save(postComment);

    }

    public void deletePostCommentById(String postId){
        postCommentRepository.delete(findById(postId));
    }
    public PostComment findById(String id){
        return postCommentRepository.findById(id).orElseThrow(() -> new PostCommentDoesNotFoundException(Constant.POSTCOMMENT_DOES_NOT_FOUND_EXCEPTION));
    }
}
