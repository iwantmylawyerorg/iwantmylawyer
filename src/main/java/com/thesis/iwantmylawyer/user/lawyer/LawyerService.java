package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.city.CityService;
import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseField;
import com.thesis.iwantmylawyer.expertisefield.ExpertiseFieldService;
import com.thesis.iwantmylawyer.minio.MinioService;
import com.thesis.iwantmylawyer.user.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;
    private final CityService cityService;
    private final LawyerConverter lawyerConverter;
    private final MinioService minioService;
    private final ExpertiseFieldService expertiseFieldService;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public LawyerService(LawyerRepository lawyerRepository, CityService cityService, LawyerConverter lawyerConverter, MinioService minioService, ExpertiseFieldService expertiseFieldService, PasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.lawyerRepository = lawyerRepository;
        this.cityService = cityService;
        this.lawyerConverter = lawyerConverter;
        this.minioService = minioService;
        this.expertiseFieldService = expertiseFieldService;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public LawyerResponse getById(String id){
        return lawyerConverter.convert(findById(id));
    }

    public Page<LawyerGetAllResponse> getAllLawyers(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return lawyerConverter.getAllConvert(lawyerRepository.findAll(pageable));
    }

    public Page<LawyerGetAllResponse> getAllLawyersWithFilter(LawyerSearchRequest lawyerSearchRequest){
        CriteriaQuery<Lawyer> criteriaQuery = criteriaBuilder.createQuery(Lawyer.class);
        Root<Lawyer> lawyerRoot = criteriaQuery.from(Lawyer.class);
        Predicate predicate = getPredicate(lawyerSearchRequest, lawyerRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Lawyer> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(lawyerSearchRequest.pageNumber() * lawyerSearchRequest.pageSize());
        typedQuery.setMaxResults(lawyerSearchRequest.pageSize());

        Pageable pageable = PageRequest.of(lawyerSearchRequest.pageNumber(), lawyerSearchRequest.pageSize());
        List<Lawyer> resultList = typedQuery.getResultList();
        long size = resultList.size();

        return lawyerConverter.getAllConvert(new PageImpl<>(resultList,pageable,size));
    }
    private Predicate getPredicate(LawyerSearchRequest lawyerSearchRequest, Root<Lawyer> lawyerRoot){
        List<Predicate> predicates = new ArrayList<>();
        if(!lawyerSearchRequest.firstName().isEmpty()){
            predicates.add(
                    criteriaBuilder.like(lawyerRoot.get("firstName"),
                            "%" + lawyerSearchRequest.firstName() + "%")
            );
        }
        if(!lawyerSearchRequest.lastName().isEmpty()){
            predicates.add(
                    criteriaBuilder.like(lawyerRoot.get("lastName"),
                            "%" + lawyerSearchRequest.lastName() + "%")
            );
        }
        if(!lawyerSearchRequest.city().isEmpty()){
            predicates.add(
                    criteriaBuilder.like(lawyerRoot.get("baroKayitIl").get("name"),
                            "%" + lawyerSearchRequest.city()+ "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }



    public void createLawyer(CreateLawyerRequest createLawyerRequest){
        if(lawyerRepository.findByEmail(createLawyerRequest.email()).isPresent()){
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
