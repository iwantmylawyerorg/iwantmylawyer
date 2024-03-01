package com.thesis.iwantmylawyer.user.lawyer;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LawyerRepository extends JpaRepository<Lawyer,String> {


    Optional<Lawyer> findByEmail(String email);
}
