package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.city.CityService;
import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseField;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldService;
import com.thesis.iwantmylawyer.mail.MailService;
import com.thesis.iwantmylawyer.mail.SendMailRequest;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.user.Role;
import com.thesis.iwantmylawyer.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;
    private final CityService cityService;
    private final LawyerConverter lawyerConverter;
    private final MinioService minioService;
    private final ExpertiseFieldService expertiseFieldService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MailService mailService;

    public LawyerService(LawyerRepository lawyerRepository, CityService cityService, LawyerConverter lawyerConverter, MinioService minioService, ExpertiseFieldService expertiseFieldService, PasswordEncoder passwordEncoder, UserService userService, MailService mailService) {
        this.lawyerRepository = lawyerRepository;
        this.cityService = cityService;
        this.lawyerConverter = lawyerConverter;
        this.minioService = minioService;
        this.expertiseFieldService = expertiseFieldService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

        this.mailService = mailService;
    }

    public LawyerResponse getById(String id){

        return lawyerConverter.convert(findById(id));
    }
    public UnconfirmedLawyerResponse getUnconfirmedLawyerById(String id){
        return lawyerConverter.unconfirmedConvert(findById(id));
    }

    public Page<LawyerGetAllResponse> getAllLawyers(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return lawyerConverter.getAllConvert(lawyerRepository.findAll(pageable));
    }

    public Page<LawyerGetAllResponse> getAllLawyersWithFilter(Integer page, Integer size,String firstName,String lastName,String city){
        Pageable pageable = PageRequest.of(page, size);
        Page<Lawyer> lawyers = lawyerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndBaroKayitIl_NameContainingIgnoreCaseAndRole(firstName.trim(), lastName.trim(), city.trim(), Role.LAWYER,pageable);

        return lawyerConverter.getAllConvert(lawyers);
    }

    public Page<LawyerGetAllResponse> getAllUnconfirmedLawyers(Integer page,Integer size){
        Pageable pageable = PageRequest.of(page, size);

        return lawyerConverter.getAllConvert(lawyerRepository.findAllByRole(Role.LAWYER_UNCONFIRMED,pageable));
    }


    @Transactional
    public void createLawyer(CreateLawyerRequest createLawyerRequest){
        if(userService.findUserByEmail(createLawyerRequest.email()).isPresent()){
            throw new LawyerAlreadyExistsException(Constant.LAWYER_ALREADY_EXISTS_EXCEPTION);
        }
        City city = cityService.findById(createLawyerRequest.cityId());
        Lawyer lawyer = new Lawyer(
                createLawyerRequest.email(),
                passwordEncoder.encode(createLawyerRequest.password()),
                createLawyerRequest.firstName(),
                createLawyerRequest.lastName(),
                createLawyerRequest.telephoneNo(),
                createLawyerRequest.tcNo(),
                createLawyerRequest.baroSicilNo(),
                city,
                Role.LAWYER_UNCONFIRMED
        );
        lawyerRepository.save(lawyer);
        SendMailRequest sendMailRequest = new SendMailRequest(lawyer.getEmail(),"Your account has been created. To activate your account as a lawyer, please complete the activate account steps in your settings.","Welcome to IWantMyLawyer");
        mailService.sendMail(sendMailRequest);
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
        lawyer.setTcPhoto(minioService.uploadImage(multipartFile));
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
        lawyer.setContactEmail(request.contactEmail() == null ? lawyer.getContactEmail() : request.contactEmail());
        lawyer.setContactTelNo(request.contactTelNo() == null ? lawyer.getContactTelNo() : request.contactTelNo());
        lawyer.setContactFaceBookUrl(request.contactFaceBookUrl() == null ? lawyer.getContactFaceBookUrl() : request.contactFaceBookUrl());
        lawyer.setContactInstagramUrl(request.contactInstagramUrl() == null ? lawyer.getContactInstagramUrl() : request.contactInstagramUrl());
        lawyer.setContactTwitterUrl(request.contactTwitterUrl() == null ? lawyer.getContactTwitterUrl() : request.contactTwitterUrl());
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
