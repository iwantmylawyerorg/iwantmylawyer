package com.thesis.iwantmylawyer.expertisefield;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expertisefield")
@Validated
public class ExpertiseFieldController {
    private final ExpertiseFieldService expertiseFieldService;

    public ExpertiseFieldController(ExpertiseFieldService expertiseFieldService) {
        this.expertiseFieldService = expertiseFieldService;
    }

    @GetMapping
    public ResponseEntity<List<ExpertiseFieldResponse>> getAllExpertiseFields(){
        return new ResponseEntity<>(expertiseFieldService.getAllExpertiseField(),HttpStatus.OK);
    }

    @PostMapping(path = "/{name}")
    public ResponseEntity<Void> createExpertiseField(
            @PathVariable("name") @NotBlank String name){

            expertiseFieldService.createExpertiseField(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
