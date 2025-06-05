package com.iolab.musicstore.musicstore.domain.upload.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = FileExistValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileExist {

    String message() default "Must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
