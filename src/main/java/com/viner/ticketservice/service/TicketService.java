package com.viner.ticketservice.service;

import com.viner.ticketservice.dto.AddOrUpdateTicketDto;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.TicketDto;
import com.viner.ticketservice.entity.Ticket;
import com.viner.ticketservice.entity.User;
import com.viner.ticketservice.exceptions.DataNotFoundException;
import com.viner.ticketservice.exceptions.EntityExistsException;
import com.viner.ticketservice.mappers.TicketMapper;
import com.viner.ticketservice.mappers.UserMapper;
import com.viner.ticketservice.repository.TicketRepository;
import com.viner.ticketservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;

    @Transactional
    public List<TicketDto> getTickets(Long planeId) {
        return ticketRepository.findTicketsByPlaneId(planeId)
                               .stream()
                               .map(ticketMapper::ticketToTicketDto)
                               .collect(Collectors.toList());
    }

    @Transactional
    public TicketDto getTicketById(Long planeId, Long ticketId) {
        Ticket ticket = getTicketEntity(planeId, ticketId);
        return ticketMapper.ticketToTicketDto(ticket);
    }

    @Transactional
    public TicketDto updateTicket(Long planeId, Long ticketId, AddOrUpdateTicketDto ticketDto) {
        Ticket foundTicket = getTicketEntity(planeId, ticketId);
        ticketMapper.updateTicketFromAddOrUpdateTicketDto(ticketDto, foundTicket);
        return ticketMapper.ticketToTicketDto(foundTicket);
    }

    @Transactional
    public TicketDto addUserToTicket(Long planeId, Long ticketId, AddOrUpdateUserDto user) {
        Ticket foundTicket = getTicketEntity(planeId, ticketId);
        User foundUser = userRepository.findByPassport(user.getPassport())
                                       .orElse(userMapper.addOrUpdateUserDtoToUser(user));
        userRepository.save(foundUser);
        if (foundTicket.getUser() != null) {
            throw new EntityExistsException(
                    "Ticket with id=" + ticketId + " of plane with id=" + planeId + " already has a passenger");
        }
        foundUser.addTicketToUser(foundTicket);
        return ticketMapper.ticketToTicketDto(foundTicket);
    }

    @Transactional
    public void markAsDeletedTicket(Long planeId, Long ticketId) {
        Ticket foundTicket = getTicketEntity(planeId, ticketId);
        foundTicket.setDeleted(true);
    }

    @Transactional
    public void deleteTicket(Long planeId, Long ticketId) {
        Ticket foundTicket = getTicketEntity(planeId, ticketId);
        foundTicket.getUser().removeTicketFromUser(foundTicket);
        foundTicket.getPlane().removeTicketFromPlane(foundTicket);
        ticketRepository.delete(foundTicket);
    }

    private Ticket getTicketEntity(Long planeId, Long ticketId) {
        return ticketRepository.findTicketByIdAndPlaneId(ticketId, planeId)
                               .orElseThrow(() -> new DataNotFoundException(
                                       "Ticket with id=" + ticketId + " not found for plane with id=" + planeId));
    }
}
