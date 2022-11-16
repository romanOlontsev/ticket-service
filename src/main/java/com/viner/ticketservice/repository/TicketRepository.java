package com.viner.ticketservice.repository;

import com.viner.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByPlaneId(Long planeId);

    Optional<Ticket> findTicketByIdAndPlaneId(Long ticketId, Long planeId);

}
