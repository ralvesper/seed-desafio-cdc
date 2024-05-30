package com.deveficiente.cdc.util;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {
    String message() default "{beanvalidation.uniquevalue}";
    Class<?> domainClass();
    String fieldName();
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
