package com.thesis.iwantmylawyer.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,String> {


    Optional<Like> findByPost_IdAndUser_Id(String postId,String userId);
}
