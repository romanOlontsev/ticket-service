package com.viner.ticketservice.dto;

import com.viner.ticketservice.annotations.ValidLandingDate;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ValidLandingDate
public class AddOrUpdatePlaneDto {
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @Positive(message = "The flight number should be positive")
    private Integer flightNumber;
    @PositiveOrZero(message = "The number of places should be positive")
    private Integer places;
    @Future(message = "Departure time should be later than now")
    private LocalDateTime depart;
    @Future(message = "Landing time should be later than now")
    private LocalDateTime landing;
    @NotBlank(message = "The field 'from' cannot be empty")
    private String from;
    @NotBlank(message = "The field 'to' cannot be empty")
    private String to;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.0")
    @Digits(integer = 5, fraction = 2,
            message = "The allowable number of digits in the integer and fractional parts is 5 and 2, respectively")
    private BigDecimal ticketPrice;
}
