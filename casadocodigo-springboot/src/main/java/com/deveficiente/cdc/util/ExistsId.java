package com.deveficiente.cdc.util;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ExistsId {

        String message() default "{beanvalidation.existsid}";

        Class<?>[] groups() default {};

        Class<?>[] payload() default {};

        String fieldName();

        Class<?> domainClass();
}
