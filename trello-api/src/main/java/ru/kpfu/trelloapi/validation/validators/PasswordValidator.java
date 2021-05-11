package ru.kpfu.trelloapi.validation.validators;

import ru.kpfu.trelloapi.validation.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() > 7 && value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*")
                && value.matches(".*[0-9].*") &&
                value.matches(".*[_&.#^*-]*.");
    }
}