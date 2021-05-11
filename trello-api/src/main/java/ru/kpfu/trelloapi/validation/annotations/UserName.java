package ru.kpfu.trelloapi.validation.annotations;


import ru.kpfu.trelloapi.validation.validators.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {

    String message() default "invalid name format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}