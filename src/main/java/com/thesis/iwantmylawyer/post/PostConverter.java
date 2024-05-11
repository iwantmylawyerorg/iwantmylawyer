package com.thesis.iwantmylawyer.post;

import com.thesis.iwantmylawyer.like.LikeConverter;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.postcomment.PostCommentConverter;
import com.thesis.iwantmylawyer.user.lawyer.LawyerConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    private final LikeConverter likeConverter;
    private final MinioService minioService;

    public PostConverter(LikeConverter likeConverter, MinioService minioService) {

        this.likeConverter = likeConverter;
        this.minioService = minioService;

    }

    public PostResponse convert(Post from) {
        return new PostResponse(
                from.getId(),
                from.getText(),
                minioService.getBase64Image(from.getPostPhoto()),
                from.getLocalDateTime(),
                likeConverter.convert(from.getLikeList()),
                new LawyerResponseWithPost(
                        from.getLawyer().getId(),
                        from.getLawyer().getLastName(),
                        from.getLawyer().getLastName(),
                        minioService.getBase64Image(from.getLawyer().getLawyerPhoto())));

    }

    public Page<PostResponse> convert(Page<Post> fromList) {
        return fromList.map(post -> new PostResponse(
                post.getId(),
                post.getText(),
                minioService.getBase64Image(post.getPostPhoto()),
                post.getLocalDateTime(),
                likeConverter.convert(post.getLikeList()),
                new LawyerResponseWithPost(
                        post.getLawyer().getId(),
                        post.getLawyer().getFirstName(),
                        post.getLawyer().getLastName(),
                        minioService.getBase64Image(post.getLawyer().getLawyerPhoto()))));
    }
}
