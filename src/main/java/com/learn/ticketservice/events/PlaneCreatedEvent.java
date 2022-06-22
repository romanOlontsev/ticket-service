package com.learn.ticketservice.events;

import com.learn.ticketservice.model.Plane;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
@RequiredArgsConstructor
public class PlaneCreatedEvent {
    private final Plane plane;
}
