package com.learn.ticketservice.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class AddUserDto {
    @NotBlank
    @Size(min = 2, max = 15, message = "name must be between 2 and 15 characters")
    private String name;

    @NotBlank
    @Size(min = 2, max = 20, message = "lastname must be between 2 and 20 characters")
    private String lastname;

    @Pattern(regexp = "\\d{4}\\s\\d{6}", message = "input format, only digit: #### ######")
    private String passport;
}
