package com.thesis.iwantmylawyer.postcomment;

import com.thesis.iwantmylawyer.user.UserConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostCommentConverter {
    private final UserConverter userConverter;

    public PostCommentConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public List<PostCommentResponse> convert(List<PostComment> fromList){
        return fromList.stream().map(postComment ->
                new PostCommentResponse(
                        postComment.getId(),
                        postComment.getText(),
                        postComment.getLocalDateTime(),
                        userConverter.convert(postComment.getUser())))
                        .toList();
    }
}
