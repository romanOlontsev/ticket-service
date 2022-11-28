package com.viner.ticketservice.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
public class AddOrUpdateTicketDto {
    @DecimalMin(value = "0.0" , inclusive = false, message = "Price must be greater than 0.0")
    @Digits(integer = 5, fraction = 2,
            message = "The allowable number of digits in the integer and fractional parts is 5 and 2, respectively")
    private BigDecimal price;
}
