package com.substring.irctc.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CoachValidator.class)
public @interface ValidCoach  {
    String message() default "Invalid Coach No.";

    Class <?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}
