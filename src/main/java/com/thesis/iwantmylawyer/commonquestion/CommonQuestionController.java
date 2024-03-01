package com.thesis.iwantmylawyer.commonquestion;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/commonquestion")
@Validated
public class CommonQuestionController {

    private final CommonQuestionService commonQuestionService;


    public CommonQuestionController(CommonQuestionService commonQuestionService) {
        this.commonQuestionService = commonQuestionService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCommonQuestionRequest request){
        commonQuestionService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotBlank String id){
        commonQuestionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
