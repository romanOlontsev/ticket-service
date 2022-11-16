package com.viner.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "planes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flightNumber;

    private String name;

    private Integer places;

    private LocalDateTime depart;

    private LocalDateTime landing;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    private boolean isDeleted;

    public void addTicketsToPlane(BigDecimal price, Integer places) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        List<Ticket> generatedTickets = Stream.generate(Ticket::new)
                                              .limit(places - this.tickets.size())
                                              .peek(it -> {
                                                  it.setPrice(price);
                                                  it.setPlane(this);
                                              })
                                              .collect(Collectors.toList());
        this.tickets.addAll(generatedTickets);
    }

    public void removeTicketFromPlane(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.setPlane(null);
    }
}