package com.thesis.iwantmylawyer.address;

import com.thesis.iwantmylawyer.constant.Constant;
import com.thesis.iwantmylawyer.user.lawyer.Lawyer;
import com.thesis.iwantmylawyer.user.lawyer.LawyerService;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final LawyerService lawyerService;

    public AddressService(AddressRepository addressRepository, LawyerService lawyerService) {
        this.addressRepository = addressRepository;
        this.lawyerService = lawyerService;
    }


    public void createAddress(CreateAddressRequest createAddressRequest){
        Lawyer lawyer = lawyerService.findById(createAddressRequest.lawyerId());
        Address address = new Address(
                createAddressRequest.street(),
                createAddressRequest.alley(),
                createAddressRequest.city(),
                createAddressRequest.state(),
                createAddressRequest.postalCode(),
                createAddressRequest.country(),
                lawyer
        );
        addressRepository.save(address);
    }
    public void updateAddress(UpdateAddressRequest updateAddressRequest){
        Address address = findById(updateAddressRequest.lawyerId());
        address.setAlley(updateAddressRequest.alley().trim().isEmpty() ? address.getAlley() : updateAddressRequest.alley());
        address.setCity(updateAddressRequest.city().trim().isEmpty() ? address.getCity() : updateAddressRequest.city());
        address.setCountry(updateAddressRequest.country().trim().isEmpty() ? address.getCountry() : updateAddressRequest.country());
        address.setStreet(updateAddressRequest.street().trim().isEmpty() ? address.getStreet() : updateAddressRequest.street());
        address.setState(updateAddressRequest.state().trim().isEmpty() ? address.getState() : updateAddressRequest.state());
        address.setPostalCode(updateAddressRequest.postalCode().trim().isEmpty() ? address.getPostalCode() : updateAddressRequest.postalCode());

        addressRepository.save(address);
    }
    public Address findById(String id){
        return addressRepository.findByLawyer_Id(id).orElseThrow(() -> new AddressNotFoundException(Constant.ADDRESS_DOES_NOT_FOUND_EXCEPTION));
    }
}
