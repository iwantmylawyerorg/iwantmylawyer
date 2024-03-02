package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.city.CityService;
import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseField;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldService;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;
    private final CityService cityService;
    private final LawyerConverter lawyerConverter;
    private final MinioService minioService;
    private final ExpertiseFieldService expertiseFieldService;

    public LawyerService(LawyerRepository lawyerRepository, CityService cityService, LawyerConverter lawyerConverter, MinioService minioService, ExpertiseFieldService expertiseFieldService) {
        this.lawyerRepository = lawyerRepository;
        this.cityService = cityService;
        this.lawyerConverter = lawyerConverter;
        this.minioService = minioService;
        this.expertiseFieldService = expertiseFieldService;
    }

    public LawyerResponse getById(String id){
        return lawyerConverter.convert(findById(id));
    }

    public Page<LawyerGetAllResponse> getAllLawyers(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return lawyerConverter.getAllConvert(lawyerRepository.findAll(pageable));
    }

    public void createLawyer(CreateLawyerRequest createLawyerRequest){
        if(lawyerRepository.findByEmail(createLawyerRequest.email()).isPresent()){
            throw new LawyerAlreadyExistsException(Constant.LAWYER_ALREADY_EXISTS_EXCEPTION);
        }
        City city = cityService.findById(createLawyerRequest.cityId());
        Lawyer lawyer = new Lawyer(
                createLawyerRequest.email(),
                createLawyerRequest.password(),
                createLawyerRequest.firstName(),
                createLawyerRequest.lastName(),
                createLawyerRequest.telephoneNo(),
                createLawyerRequest.tcNo(),
                createLawyerRequest.baroSicilNo(),
                city
        );
        lawyerRepository.save(lawyer);
    }
    public Lawyer findById(String id){
        return lawyerRepository.findById(id).orElseThrow(() -> new LawyerNotFoundException(Constant.LAWYER_DOES_NOT_FOUND_EXCEPTION));
    }

    public void updateLawyer(UpdateLawyerRequest updateLawyerRequest){
        if(lawyerRepository.findByEmail(updateLawyerRequest.email()).isPresent()){
            throw new LawyerAlreadyExistsException(Constant.LAWYER_ALREADY_EXISTS_EXCEPTION);
        }
        Lawyer lawyer = findById(updateLawyerRequest.id());
        lawyer.setEmail(updateLawyerRequest.email().trim().isEmpty() ? lawyer.getEmail() : updateLawyerRequest.email());
        lawyer.setFirstName(updateLawyerRequest.firstName().trim().isEmpty() ? lawyer.getFirstName() : updateLawyerRequest.firstName());
        lawyer.setLastName(updateLawyerRequest.lastName().trim().isEmpty() ? lawyer.getLastName() : updateLawyerRequest.lastName());
        lawyer.setPassword(updateLawyerRequest.password().trim().isEmpty() ? lawyer.getPassword() : updateLawyerRequest.password());
        lawyer.setTelephoneNo(updateLawyerRequest.telephoneNo().trim().isEmpty() ? lawyer.getTelephoneNo() : updateLawyerRequest.telephoneNo());

        lawyerRepository.save(lawyer);
    }

    public void addTcPhoto(String id,MultipartFile multipartFile){
        Lawyer lawyer = findById(id);
        lawyer.setLawyerPhoto(minioService.uploadImage(multipartFile));
        lawyerRepository.save(lawyer);
    }
    public void addAvukatKartPhoto(String id,MultipartFile multipartFile){
        Lawyer lawyer = findById(id);
        lawyer.setAvukatKartPhoto(minioService.uploadImage(multipartFile));
        lawyerRepository.save(lawyer);
    }
    public void addLawyerPhoto(String id,MultipartFile multipartFile){
        Lawyer lawyer = findById(id);
        lawyer.setLawyerPhoto(minioService.uploadImage(multipartFile));
        lawyerRepository.save(lawyer);
    }
    public void updateAboutMe(String id,String aboutMe){
        Lawyer lawyer = findById(id);
        lawyer.setAboutMe(aboutMe);
        lawyerRepository.save(lawyer);
    }
    public void updateSocialContact(UpdateSocialContactRequest request){
        Lawyer lawyer = findById(request.id());
        lawyer.setContactEmail(request.contactEmail().trim().isEmpty() ? lawyer.getContactEmail() : request.contactEmail());
        lawyer.setContactTelNo(request.contactTelNo().trim().isEmpty() ? lawyer.getContactTelNo() : request.contactTelNo());
        lawyer.setContactFaceBookUrl(request.contactFaceBookUrl().trim().isEmpty() ? lawyer.getContactFaceBookUrl() : request.contactFaceBookUrl());
        lawyer.setContactInstagramUrl(request.contactInstagramUrl().trim().isEmpty() ? lawyer.getContactInstagramUrl() : request.contactInstagramUrl());
        lawyer.setContactTwitterUrl(request.contactTwitterUrl().trim().isEmpty() ? lawyer.getContactTwitterUrl() : request.contactTwitterUrl());
        lawyerRepository.save(lawyer);
    }

    public void addExpertiseField(AddExpertiseFieldRequest request) {
        Lawyer lawyer = findById(request.id());
        List<ExpertiseField> expertiseFields = expertiseFieldService.findByIdIn(request.expertiseFieldIdList());
        lawyer.getExpertiseFieldList().addAll(expertiseFields);
        lawyerRepository.save(lawyer);
    }
    public void removeExpertiseField(AddExpertiseFieldRequest request) {
        Lawyer lawyer = findById(request.id());
        List<ExpertiseField> expertiseFields = expertiseFieldService.findByIdIn(request.expertiseFieldIdList());
        lawyer.getExpertiseFieldList().removeAll(expertiseFields);
        lawyerRepository.save(lawyer);
    }
    public void addRoleForLawyer(String lawyerId){
        Lawyer lawyer = findById(lawyerId);
        lawyer.setRole(Role.LAWYER);
        lawyerRepository.save(lawyer);
    }

}
