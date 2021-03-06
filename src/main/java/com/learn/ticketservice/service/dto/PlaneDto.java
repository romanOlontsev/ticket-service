package com.learn.ticketservice.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlaneDto {
    private long id;
    private String name;
    private Integer places;
    private LocalDateTime depart;
    private Duration duration;
    private String from;
    private String to;
    private List<TicketDto> tickets;
    private boolean isDeleted;

    @Data
    public static class TicketDto{
        private Long id;
        private BigDecimal price;
        private boolean isDeleted;
    }
}
