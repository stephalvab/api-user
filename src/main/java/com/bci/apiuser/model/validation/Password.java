package com.bci.apiuser.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@NotNull
//@Pattern(regexp = "${password.regex}", message = "La contraseña no cumple con el formato requerido")
@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "La contraseña no cumple con el formato requerido")
@ReportAsSingleViolation
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Password {
    String message() default "La contraseña no cumple con el formato requerido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

