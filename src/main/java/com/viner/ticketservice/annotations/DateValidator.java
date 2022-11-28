package com.viner.ticketservice.annotations;

import com.viner.ticketservice.dto.AddOrUpdatePlaneDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidLandingDate, AddOrUpdatePlaneDto> {
    @Override
    public void initialize(ValidLandingDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(AddOrUpdatePlaneDto form, ConstraintValidatorContext context) {
        if (form == null) {
            return true;
        }
        return form.getLanding()
                   .isAfter(form.getDepart());
    }
}
