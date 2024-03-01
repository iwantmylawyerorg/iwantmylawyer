package com.thesis.iwantmylawyer.user.lawyer;

import com.thesis.iwantmylawyer.city.City;
import com.thesis.iwantmylawyer.city.CityService;
import com.thesis.iwantmylawyer.exception.Constant;
import com.thesis.iwantmylawyer.user.client.ClientAlreadyExistsException;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawyerService {
    private final LawyerRepository lawyerRepository;
    private final CityService cityService;
    private final LawyerConverter lawyerConverter;

    public LawyerService(LawyerRepository lawyerRepository, CityService cityService, LawyerConverter lawyerConverter) {
        this.lawyerRepository = lawyerRepository;
        this.cityService = cityService;
        this.lawyerConverter = lawyerConverter;
    }

    public LawyerResponse getById(String id){
        return lawyerConverter.convert(findById(id));
    }
    public Page<List<LawyerGetAllResponse>> getAllLawyers(int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return lawyerConverter.getAllConvert(lawyerRepository.findAllBy(pageable));
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

}
