package com.learn.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Plane")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plane {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer places;

    private LocalDateTime depart;

    private Duration duration;

    @Column(name = "\"FROM\"")
    private String from;

    private String to;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets;

    private boolean isDeleted;

    public void associate(List<Ticket> tickets) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.addAll(tickets);

        tickets.forEach(it -> it.setPlane(this));
    }
}
