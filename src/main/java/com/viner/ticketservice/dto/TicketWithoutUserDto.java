package com.viner.ticketservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketWithoutUserDto {
    private Long id;
    private BigDecimal price;
    private boolean isDeleted;
}
