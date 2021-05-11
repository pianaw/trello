package ru.kpfu.trelloapi.validation.annotations;

import ru.kpfu.trelloapi.validation.validators.EmailValidator;
import ru.kpfu.trelloapi.validation.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
