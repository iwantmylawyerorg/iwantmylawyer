package com.thesis.iwantmylawyer.validator;

import com.google.common.base.Joiner;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint,String> {
    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx) {
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                new CharacterRule(EnglishCharacterData.Special,1),
                new WhitespaceRule()));

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if(result.isValid()) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(
                        Joiner.on(",").join(passwordValidator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
