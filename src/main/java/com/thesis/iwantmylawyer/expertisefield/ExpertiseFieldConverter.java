package com.thesis.iwantmylawyer.expertisefield;

import com.thesis.iwantmylawyer.minio.MinioService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpertiseFieldConverter {
    private final MinioService minioService;

    public ExpertiseFieldConverter(MinioService minioService) {
        this.minioService = minioService;
    }

    public List<ExpertiseFieldResponse> convert(List<ExpertiseField> fromList){
        return fromList.stream().map(expertiseField -> new ExpertiseFieldResponse(
                expertiseField.getId(),
                expertiseField.getName(),
                expertiseField.getDescription(),
                minioService.getBase64Image(expertiseField.getPhoto()))).toList();
    }
}
