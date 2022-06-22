package com.learn.ticketservice.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AddPlaneDto {
    @NotBlank
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;

    @Digits(integer = 4, fraction = 0)
    @Positive
    private Integer places;

    @NotNull
    @FutureOrPresent
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
