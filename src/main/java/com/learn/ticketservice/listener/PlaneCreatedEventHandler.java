package com.learn.ticketservice.listener;

import com.learn.ticketservice.events.PlaneCreatedEvent;
import com.learn.ticketservice.model.Ticket;
import com.learn.ticketservice.repository.PlaneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@AllArgsConstructor
public class PlaneCreatedEventHandler {

    private final PlaneRepository planeRepository;

    @EventListener
    public void onEvent(PlaneCreatedEvent planeCreatedEvent) {
//        log.info(planeCreatedEvent.toString());
        List<Ticket> tickets = IntStream.range(0, planeCreatedEvent.getPlane().getPlaces())
                .mapToObj(it -> Ticket.builder()
                        .price(BigDecimal.ZERO)
                        .isDeleted(false)
                        .build())
                .collect(Collectors.toList());

        planeCreatedEvent.getPlane().associate(tickets);
        planeRepository.save(planeCreatedEvent.getPlane());
    }
}
