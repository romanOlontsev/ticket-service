package com.viner.ticketservice.dto;

import lombok.Data;

@Data
public class AddOrUpdateUserDto {
    private String name;
    private String lastName;
    private String passport;
}
