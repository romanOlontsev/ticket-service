package com.viner.ticketservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOrUpdateTicketDto {
    private BigDecimal price;
}
