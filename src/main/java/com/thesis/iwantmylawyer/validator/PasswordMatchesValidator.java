package com.thesis.iwantmylawyer.validator;

import com.google.common.base.Joiner;
import com.thesis.iwantmylawyer.user.client.CreateClientRequest;
import com.thesis.iwantmylawyer.user.lawyer.CreateLawyerRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Hello world");
        /*
        if(o instanceof CreateClientRequest createClientRequest){
            return createClientRequest.password().equals(createClientRequest.passwordMatch());
        } else if (o instanceof CreateLawyerRequest createLawyerRequest) {
            return createLawyerRequest.password().equals(createLawyerRequest.passwordMatch());
        } */

        return false;
    }


}
