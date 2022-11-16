package com.viner.ticketservice.mappers;

import com.viner.ticketservice.dto.AddOrUpdateTicketDto;
import com.viner.ticketservice.dto.TicketDto;
import com.viner.ticketservice.dto.TicketWithoutUserDto;
import com.viner.ticketservice.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto ticketToTicketDto(Ticket ticket);

    Ticket ticketDtoToTicket(TicketDto ticketDto);

    AddOrUpdateTicketDto ticketToAddOrUpdateTicketDto(Ticket ticket);

    Ticket addOrUpdateTicketDtoToTicket(AddOrUpdateTicketDto addOrUpdateTicketDto);

    List<TicketDto> ticketListToTicketDtoList(List<Ticket> tickets);

    List<TicketWithoutUserDto> ticketListToTicketWithoutUserDtoList(List<Ticket> ticket);

    void updateTicketFromAddOrUpdateTicketDto(AddOrUpdateTicketDto addOrUpdateTicketDto, @MappingTarget Ticket ticket);

}
