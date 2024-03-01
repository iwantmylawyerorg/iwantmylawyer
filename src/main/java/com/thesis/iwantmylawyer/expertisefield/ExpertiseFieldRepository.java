package com.thesis.iwantmylawyer.expertisefield;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpertiseFieldRepository extends JpaRepository<ExpertiseField,String> {


    Optional<ExpertiseField> findByName(String name);

    List<ExpertiseField> findByIdIn(List<String> idList);
}
