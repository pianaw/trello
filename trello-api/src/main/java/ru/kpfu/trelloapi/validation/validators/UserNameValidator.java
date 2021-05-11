package ru.kpfu.trelloapi.validation.validators;

import ru.kpfu.trelloapi.validation.annotations.UserName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.length() > 1 &&!(value.matches(".*[\\d].*")) && !(value.matches(".*[^A-Za-z0-9].*")) ;

    }
}
