package com.viner.ticketservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlaneDto {
    private Long id;
    private String name;
    private Integer flightNumber;
    private Integer places;
    private LocalDateTime depart;
    private LocalDateTime landing;
    private String from;
    private String to;
    private List<TicketDto> tickets;
    private boolean isDeleted;

}
