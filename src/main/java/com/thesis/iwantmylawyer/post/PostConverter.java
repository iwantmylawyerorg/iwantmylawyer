package com.thesis.iwantmylawyer.post;

import com.thesis.iwantmylawyer.like.LikeConverter;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.postcomment.PostCommentConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostConverter {
    private final PostCommentConverter postCommentConverter;
    private final LikeConverter likeConverter;
    private final MinioService minioService;

    public PostConverter(PostCommentConverter postCommentConverter, LikeConverter likeConverter, MinioService minioService) {
        this.postCommentConverter = postCommentConverter;
        this.likeConverter = likeConverter;
        this.minioService = minioService;
    }

    public PostResponse convert(Post from){
        return new PostResponse(
                from.getId(),
                from.getText(),
                minioService.getBase64Image(from.getPostPhoto()),
                from.getLocalDateTime(),
                postCommentConverter.convert(from.getPostCommentList()),
                likeConverter.convert(from.getLikeList()));

    }

    public Page<PostResponse> convert(Page<Post> fromList){
        return fromList.map(post -> new PostResponse(
                        post.getId(),
                        post.getText(),
                        minioService.getBase64Image(post.getPostPhoto()),
                        post.getLocalDateTime(),
                        postCommentConverter.convert(post.getPostCommentList()),
                        likeConverter.convert(post.getLikeList())));
    }
}
