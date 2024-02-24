package com.thesis.iwantmylawyer.user.lawyer;

import org.springframework.stereotype.Service;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;

    public LawyerService(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    public void createLawyer(CreateLawyerRequest createLawyerRequest){

    }
}
