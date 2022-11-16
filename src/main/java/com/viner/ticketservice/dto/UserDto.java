package com.viner.ticketservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String passport;
    private List<TicketWithoutUserDto> tickets;
    private boolean isDeleted;
}