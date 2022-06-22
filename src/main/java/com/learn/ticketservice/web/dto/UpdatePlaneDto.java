package com.learn.ticketservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
public class UpdatePlaneDto {
    @NotBlank
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;

//    @Digits(integer = 4, fraction = 0)
//    @Positive
//    private Integer places;

    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime depart;

    @NotNull
    private Duration duration;

    @NotBlank
    @Size(min = 2, max = 40)
    private String from;

    @NotBlank
    @Size(min = 2, max = 40)
    private String to;
}
