package com.thesis.iwantmylawyer.user.lawyer;


import com.thesis.iwantmylawyer.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LawyerRepository extends JpaRepository<Lawyer, String> {


    Optional<Lawyer> findByEmail(String email);

    Page<Lawyer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndBaroKayitIl_NameContainingIgnoreCaseAndRole(String firstName, String lastName, String city, Role role,Pageable pageable);

    Page<Lawyer> findAllByRole(Role role,Pageable pageable);

}
