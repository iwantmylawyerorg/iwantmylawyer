package com.thesis.iwantmylawyer.article;

import com.thesis.iwantmylawyer.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,String> {

}
