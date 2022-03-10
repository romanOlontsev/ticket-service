package com.learn.ticketservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlaneDto {
    private long id;
    private String name;
    private Integer places;
    private LocalDate dapart;
    private Duration duration;
    private String from;
    private String to;
    //    private List<Ticket> tickets;
    private boolean isDeleted;
}
