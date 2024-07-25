package com.thesis.iwantmylawyer.address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@Validated
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest createAddressRequest){
        addressService.createAddress(createAddressRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("(hasAnyRole('LAWYER','LAWYER_UNCONFIRMED'))")
    @PutMapping()
    public ResponseEntity<Void> updateAddress(@RequestBody UpdateAddressRequest updateAddressRequest) {
        addressService.updateAddress(updateAddressRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
