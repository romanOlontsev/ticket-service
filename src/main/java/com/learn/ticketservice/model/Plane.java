package com.learn.ticketservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Data
public class Plane {
    @Id
    private Long id;
    private String name;
    private Integer places;
    private LocalDate dapart;
    private Duration duration;
    private String from;
    private String to;
//    private List<Ticket> tickets;
    private boolean isDeleted;
}
