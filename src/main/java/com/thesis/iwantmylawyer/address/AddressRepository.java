package com.thesis.iwantmylawyer.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,String> {

    Optional<Address> findByLawyer_Id(String lawyerId);
}
