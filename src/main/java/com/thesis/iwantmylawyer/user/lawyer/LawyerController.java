package com.thesis.iwantmylawyer.user.lawyer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/lawyer")
@Validated
public class LawyerController {

    private final LawyerService lawyerService;

    public LawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LawyerResponse> getById(@PathVariable @NotBlank String id){
        return new ResponseEntity<>(lawyerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllLawyers(@PathVariable @NotNull Integer page,
                                                                    @PathVariable @NotNull Integer size){
        return new ResponseEntity<>(lawyerService.getAllLawyers(page, size),HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllLawyersWithFilter(LawyerSearchRequest lawyerSearchRequest){
        return new ResponseEntity<>(lawyerService.getAllLawyersWithFilter(lawyerSearchRequest),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createLawyer(@RequestBody @Valid CreateLawyerRequest createLawyerRequest){
        lawyerService.createLawyer(createLawyerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateLawyer")
    public ResponseEntity<Void> updateLawyer(@RequestBody UpdateLawyerRequest updateLawyerRequest){
        lawyerService.updateLawyer(updateLawyerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addTcPhoto/{id}", consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addTcPhoto(@PathVariable @NotBlank String id,
                                           @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addTcPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addAvukatKartPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addAvukatKartPhoto(@PathVariable @NotBlank  String id,
                                                   @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addAvukatKartPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addLawyerPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addLawyerPhoto(@PathVariable @NotBlank  String id,
                                               @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addLawyerPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateAboutMe/{id}")
    public ResponseEntity<Void> updateAboutMe(@PathVariable @NotBlank String id,@RequestBody String aboutMe){
        lawyerService.updateAboutMe(id, aboutMe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateSocialContact")
    public ResponseEntity<Void> updateSocialContact(@RequestBody UpdateSocialContactRequest request){
        lawyerService.updateSocialContact(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/addExpertiseField")
    public ResponseEntity<Void> addExpertiseField(@RequestBody AddExpertiseFieldRequest request) {
        lawyerService.addExpertiseField(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/removeExpertiseField")
    public ResponseEntity<Void> removeExpertiseField(@RequestBody AddExpertiseFieldRequest request){
        lawyerService.removeExpertiseField(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/addRoleToLawyer/{id}")
    public ResponseEntity<Void> addRoleToLawyer(@PathVariable String id){
        lawyerService.addRoleForLawyer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
