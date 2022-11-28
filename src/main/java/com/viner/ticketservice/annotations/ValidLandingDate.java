package com.viner.ticketservice.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidLandingDate {

    String message() default "The landing date should be after the depart date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
