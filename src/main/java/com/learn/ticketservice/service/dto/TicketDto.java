package com.learn.ticketservice.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDto {

    private Long id;
    //    private PlaneDto plane;
    private BigDecimal price;
    private boolean isDeleted;

//    @Data
//    public static class PlaneDto{
//        private long id;
//        private String name;
//        private Integer places;
//        private LocalDate depart;
//        private Duration duration;
//        private String from;
//        private String to;
//        private boolean isDeleted;
//    }

    private UserDto user;

    @Data
    public static class UserDto {
        private Long id;
        private String name;
        private String lastname;
        private String passport;
        private boolean isDeleted;
    }
}
