package com.learn.ticketservice.web;

import com.learn.ticketservice.service.TicketService;
import com.learn.ticketservice.service.dto.TicketDto;
import com.learn.ticketservice.web.dto.UpdateTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/planes")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{planeId}/tickets/{ticketId}")
    public TicketDto ticket(
            @PathVariable("planeId") Long planeId,
            @PathVariable("ticketId") Long ticketId) {
        return ticketService.getTicketById(planeId, ticketId);
    }

    @GetMapping("/{planeId}/tickets")
    public List<TicketDto> tickets(@PathVariable("planeId") Long planeId,
                                   @RequestParam(required = false) Boolean isSold) {
        return ticketService.getTickets(planeId, isSold);
    }

    @PutMapping("/{planeId}/tickets/{ticketId}")
    public TicketDto updateTicket(@PathVariable("planeId") Long planeId,
                             @PathVariable("ticketId") Long ticketId,
                             @RequestBody @Valid UpdateTicketDto updateTicketDto) {
        return ticketService.updateTicket(planeId, ticketId, updateTicketDto);
    }

    @PatchMapping("/{planeId}/tickets/{ticketId}")
    public void deleteTicket(@PathVariable("planeId") Long planeId,
                             @PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(planeId, ticketId);
    }
}
