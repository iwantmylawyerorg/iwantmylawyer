package com.thesis.iwantmylawyer.expertisefield;

import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.minio.MinioService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExpertiseFieldService {
    private final ExpertiseFieldRepository expertiseFieldRepository;
    private final MinioService minioService;

    private final ExpertiseFieldConverter expertiseFieldConverter;


    public ExpertiseFieldService(ExpertiseFieldRepository expertiseFieldRepository, MinioService minioService, ExpertiseFieldConverter expertiseFieldConverter) {
        this.expertiseFieldRepository = expertiseFieldRepository;
        this.minioService = minioService;
        this.expertiseFieldConverter = expertiseFieldConverter;
    }

    public List<ExpertiseFieldResponse> getAllExpertiseField(){
        return expertiseFieldConverter.convert(expertiseFieldRepository.findAll());
    }

    public void createExpertiseField(String name,String description, MultipartFile multipartFile){
        if(expertiseFieldRepository.findByName(name).isPresent()){
            throw new ExpertiseFieldAlreadyExistsException(Constant.EXPERTISE_FIELD_ALREADY_EXISTS_EXCEPTION);
        }

        ExpertiseField expertiseField = new ExpertiseField(
                name,
                description,
                minioService.uploadImage(multipartFile)
                );

        expertiseFieldRepository.save(expertiseField);
    }



}