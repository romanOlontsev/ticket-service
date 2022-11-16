package com.viner.ticketservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddOrUpdatePlaneDto {
    private String name;
    private Integer flightNumber;
    private Integer places;
    private LocalDateTime depart;
    private LocalDateTime landing;
    private String from;
    private String to;
    private BigDecimal ticketPrice;
}
