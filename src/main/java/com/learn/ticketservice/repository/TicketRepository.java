package com.learn.ticketservice.repository;

import com.learn.ticketservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findTicketByIdAndPlane_Id(Long ticketId, Long planeId);

    List<Ticket> findTicketByPlane_Id(Long planeId);

    List<Ticket> findTicketByPlane_IdAndUserNotNull(Long planeId);

    List<Ticket> findTicketByPlane_IdAndUserIsNull(Long planeId);
}