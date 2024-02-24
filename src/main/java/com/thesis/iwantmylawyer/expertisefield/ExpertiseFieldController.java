package com.thesis.iwantmylawyer.expertisefield;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expertisefield")
public class ExpertiseFieldController {
    private final ExpertiseFieldService expertiseFieldService;

    public ExpertiseFieldController(ExpertiseFieldService expertiseFieldService) {
        this.expertiseFieldService = expertiseFieldService;
    }

    @GetMapping
    public ResponseEntity<List<ExpertiseFieldResponse>> getAllExpertiseFields(){
        return new ResponseEntity<>(expertiseFieldService.getAllExpertiseField(),HttpStatus.OK);
    }

    @PostMapping(consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> createExpertiseField(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestPart("file") MultipartFile file) {
            expertiseFieldService.createExpertiseField(name,description,file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
