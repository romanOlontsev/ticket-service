package com.learn.ticketservice.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String passport;
    private List<TicketDto> tickets;
    private boolean isDeleted;

    @Data
    public static class TicketDto{
        private Long id;
        //    private PlaneDto plane;
        private BigDecimal price;
        private boolean isDeleted;
    }
}
