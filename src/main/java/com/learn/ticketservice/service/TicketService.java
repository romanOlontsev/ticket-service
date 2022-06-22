package com.learn.ticketservice.service;

import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.exception.TicketIsSoldException;
import com.learn.ticketservice.model.Ticket;
import com.learn.ticketservice.model.User;
import com.learn.ticketservice.repository.TicketRepository;
import com.learn.ticketservice.service.dto.TicketDto;
import com.learn.ticketservice.service.dto.UserDto;
import com.learn.ticketservice.web.dto.UpdateTicketDto;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final MapperFacade mapper;

    @Transactional
    public TicketDto getTicketById(Long planeId, Long ticketId) {
        Ticket ticket = getTicketEntity(planeId, ticketId);
        return mapper.map(ticket, TicketDto.class);
    }

    @Transactional
    public List<TicketDto> getTickets(Long planeId, Boolean isSold) {
        List<Ticket> tickets;
        if (isSold == null) {
            tickets = ticketRepository.findTicketByPlane_Id(planeId);
        } else if (isSold) {
            tickets = ticketRepository.findTicketByPlane_IdAndUserNotNull(planeId);
        } else {
            tickets = ticketRepository.findTicketByPlane_IdAndUserIsNull(planeId);
        }
        return tickets
                .stream()
                .map(it -> mapper.map(it, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public TicketDto updateTicket(Long planeId, Long ticketId, UpdateTicketDto updateTicketDto) {
        Ticket ticket = getTicketEntity(planeId, ticketId);
        validateTicket(updateTicketDto, ticket);
        User user = userService.getUserEntity(updateTicketDto.getUserId());
        ticket.setPrice(updateTicketDto.getPrice());
        ticket.setUser(user);
        user.getTickets().add(ticket);
        return mapper.map(ticket, TicketDto.class);
    }

    private void validateTicket(UpdateTicketDto updateTicketDto, Ticket ticket) {
        if (updateTicketDto.getUserId() != null && ticket.getUser() != null) {
            throw new TicketIsSoldException("ticket is sold");
        }
    }

    @Transactional
    public void deleteTicket(Long planeId, Long ticketId) {
        Ticket ticket = getTicketEntity(planeId, ticketId);
        ticket.setDeleted(true);
    }

    public Ticket getTicketEntity(Long planeId, Long ticketId) {
        return ticketRepository.findTicketByIdAndPlane_Id(ticketId, planeId)
                .orElseThrow(() -> new DataNotFoundException("ticket not found"));
    }
}
