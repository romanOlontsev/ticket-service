package com.viner.ticketservice.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto {
    private Long id;
    private BigDecimal price;
    private boolean isDeleted;
    private UserWithoutTicketDto user;
}
