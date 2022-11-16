package com.viner.ticketservice.dto;

import lombok.Data;

@Data
public class UserWithoutTicketDto {
    private Long id;
    private String name;
    private String lastName;
    private String passport;
    private boolean isDeleted;
}
