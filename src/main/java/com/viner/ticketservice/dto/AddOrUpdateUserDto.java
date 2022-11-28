package com.viner.ticketservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AddOrUpdateUserDto {
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @NotBlank(message = "The lastname cannot be empty")
    private String lastName;
    @Pattern(regexp = "\\d{4}\\s\\d{6}", message = "input format #### ######")
    private String passport;
}
