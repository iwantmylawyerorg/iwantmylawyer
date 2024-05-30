package com.thesis.iwantmylawyer.commonquestion;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCommonQuestionRequest request){
        commonQuestionService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotBlank String id){
        commonQuestionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
