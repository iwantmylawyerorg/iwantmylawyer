package com.thesis.iwantmylawyer.postcomment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment,String> {
}
