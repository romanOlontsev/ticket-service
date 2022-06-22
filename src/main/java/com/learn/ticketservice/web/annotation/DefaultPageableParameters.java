package com.learn.ticketservice.web.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Inherited
@Target({PARAMETER, METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(name = "page", in = ParameterIn.QUERY, description = "Page number (0..n)",
        schema = @Schema(type = "integer", defaultValue = "0"))
@Parameter(name = "size", in = ParameterIn.QUERY, description = "Page size",
        schema = @Schema(type = "integer", defaultValue = "20"))
@Parameter(name = "sort", in = ParameterIn.QUERY, description = "Sorting criteria: name,asc|desc",
        array = @ArraySchema(schema = @Schema(type = "string")))
public @interface DefaultPageableParameters {
}
