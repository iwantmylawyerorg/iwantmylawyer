package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.expertisefield.ExpertiseField;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lawyer")
public class LawyerController {

    private final LawyerService lawyerService;

    public LawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LawyerResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(lawyerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<LawyerGetAllResponse>> getAllLawyers(@PathVariable Integer page,@PathVariable Integer size){
        return new ResponseEntity<>(lawyerService.getAllLawyers(page, size),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createLawyer(@RequestBody CreateLawyerRequest createLawyerRequest){
        lawyerService.createLawyer(createLawyerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/updateLawyer")
    public ResponseEntity<Void> updateLawyer(@RequestBody UpdateLawyerRequest updateLawyerRequest){
        lawyerService.updateLawyer(updateLawyerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addTcPhoto/{id}", consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addTcPhoto(@PathVariable String id,@RequestPart("file") MultipartFile file){
        lawyerService.addTcPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addAvukatKartPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addAvukatKartPhoto(@PathVariable String id,@RequestPart("file") MultipartFile file){
        lawyerService.addAvukatKartPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(path = "/addLawyerPhoto/{id}",consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> addLawyerPhoto(@PathVariable String id,@RequestPart("file") MultipartFile file){
        lawyerService.addLawyerPhoto(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateAboutMe/{id}")
    public ResponseEntity<Void> updateAboutMe(@PathVariable String id,@RequestBody String aboutMe){
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


}
