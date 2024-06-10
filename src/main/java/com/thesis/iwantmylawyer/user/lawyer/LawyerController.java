package com.thesis.iwantmylawyer.user.lawyer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/unconfirmedLawyer/{id}")
    public ResponseEntity<UnconfirmedLawyerResponse> getUnconfirmedById(@PathVariable @NotBlank String id){
        return new ResponseEntity<>(lawyerService.getUnconfirmedLawyerById(id),HttpStatus.OK);
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllLawyers(@PathVariable @NotNull Integer page,
                                                                    @PathVariable @NotNull Integer size){
        return new ResponseEntity<>(lawyerService.getAllLawyers(page, size),HttpStatus.OK);
    }
    @GetMapping("/unconfirmedLawyers/{page}/{size}")
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllUnconfirmedLawyers(@PathVariable @NotNull Integer page,
                                                                               @PathVariable @NotNull Integer size){
        return new ResponseEntity<>(lawyerService.getAllUnconfirmedLawyers(page, size),HttpStatus.OK);
    }
   @GetMapping("/{page}/{size}/{firstName}/{lastName}/{city}")
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllLawyersWithFilter(@PathVariable(required = false) Integer page,
                                                                              @PathVariable(required = false) Integer size,
                                                                              @PathVariable(required = false) String firstName,
                                                                              @PathVariable(required = false) String lastName,
                                                                              @PathVariable(required = false) String city){
        return new ResponseEntity<>(lawyerService.getAllLawyersWithFilter(page, size, firstName, lastName, city),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> createLawyer(@RequestBody @Valid CreateLawyerRequest createLawyerRequest){
        lawyerService.createLawyer(createLawyerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping("/updateLawyer")
    public ResponseEntity<Void> updateLawyer(@RequestBody UpdateLawyerRequest updateLawyerRequest){
        lawyerService.updateLawyer(updateLawyerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping(path = "/addTcPhoto/{id}", consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addTcPhoto(@PathVariable @NotBlank String id,
                                           @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addTcPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping(path = "/addAvukatKartPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addAvukatKartPhoto(@PathVariable @NotBlank  String id,
                                                   @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addAvukatKartPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping(path = "/addLawyerPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addLawyerPhoto(@PathVariable @NotBlank  String id,
                                               @RequestPart("file") @NotNull MultipartFile file){
        lawyerService.addLawyerPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping("/updateAboutMe/{id}")
    public ResponseEntity<Void> updateAboutMe(@PathVariable @NotBlank String id,@RequestBody String aboutMe){
        lawyerService.updateAboutMe(id, aboutMe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping("/updateSocialContact")
    public ResponseEntity<Void> updateSocialContact(@RequestBody UpdateSocialContactRequest request){
        lawyerService.updateSocialContact(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping("/addExpertiseField")
    public ResponseEntity<Void> addExpertiseField(@RequestBody AddExpertiseFieldRequest request) {
        lawyerService.addExpertiseField(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping("/removeExpertiseField")
    public ResponseEntity<Void> removeExpertiseField(@RequestBody AddExpertiseFieldRequest request){
        lawyerService.removeExpertiseField(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("(hasAnyRole('ADMIN'))")
    @PutMapping("/addRoleToLawyer/{id}")
    public ResponseEntity<Void> addRoleToLawyer(@PathVariable String id){
        lawyerService.addRoleForLawyer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
