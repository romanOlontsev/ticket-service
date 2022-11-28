package com.viner.ticketservice.web;

import com.viner.ticketservice.dto.AddOrUpdateTicketDto;
import com.viner.ticketservice.dto.AddOrUpdateUserDto;
import com.viner.ticketservice.dto.TicketDto;
import com.viner.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{planeId}/tickets")
    public List<TicketDto> getTickets(@PathVariable Long planeId) {
        return ticketService.getTickets(planeId);
    }

    @GetMapping("/{planeId}/tickets/{ticketId}")
    public TicketDto getTicketById(@PathVariable Long planeId,
                                   @PathVariable Long ticketId) {
        return ticketService.getTicketById(planeId, ticketId);
    }

    @PutMapping("/{planeId}/tickets/{ticketId}")
    public TicketDto updateTicket(@PathVariable Long planeId,
                                  @PathVariable Long ticketId,
                                  @Valid @RequestBody AddOrUpdateTicketDto ticketDto) {
        return ticketService.updateTicket(planeId, ticketId, ticketDto);
    }

    @PatchMapping("/{planeId}/tickets/{ticketId}/passenger")
    public TicketDto addUserToTicket(@PathVariable Long planeId,
                                     @PathVariable Long ticketId,
                                     @Valid @RequestBody AddOrUpdateUserDto user) {
        return ticketService.addUserToTicket(planeId, ticketId, user);
    }

    @PatchMapping("/{planeId}/tickets/{ticketId}")
    public void markAsDeletedTicket(@PathVariable Long planeId,
                                    @PathVariable Long ticketId) {
        ticketService.markAsDeletedTicket(planeId, ticketId);
    }

    @DeleteMapping("/{planeId}/tickets/{ticketId}")
    public void deleteTicket(@PathVariable Long planeId,
                             @PathVariable Long ticketId) {
        ticketService.deleteTicket(planeId, ticketId);
    }
}
