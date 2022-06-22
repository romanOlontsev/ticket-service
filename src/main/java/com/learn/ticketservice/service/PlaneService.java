package com.learn.ticketservice.service;

import com.learn.ticketservice.events.PlaneCreatedEvent;
import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.exception.EntityExistsException;
import com.learn.ticketservice.model.Plane;
import com.learn.ticketservice.repository.PlaneRepository;
import com.learn.ticketservice.repository.TicketRepository;
import com.learn.ticketservice.service.dto.PlaneDto;
import com.learn.ticketservice.web.dto.AddPlaneDto;
import com.learn.ticketservice.web.dto.UpdatePlaneDto;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;
    private final MapperFacade mapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public PlaneDto getPlaneById(Long planeId) {
        return mapper.map(getPlaneEntity(planeId), PlaneDto.class);
    }

    @Transactional
    public Page<PlaneDto> getPlanes(LocalDateTime localDateTime, Pageable pageable) {
        return planeRepository.findPlanesByDepartAfterCurrentDate(localDateTime, pageable)
                .map(it -> mapper.map(it, PlaneDto.class));
    }

    @Transactional
    public PlaneDto addNewPlane(AddPlaneDto plane) {
        planeRepository.findPlaneByName(plane.getName())
                .ifPresent(s -> {
                    throw new EntityExistsException(
                            String.format("plane with name %s already exists", plane.getName()));
                });
        Plane savedPlane = planeRepository.save(mapper.map(plane, Plane.class));
        applicationEventPublisher.publishEvent(new PlaneCreatedEvent(savedPlane));
        return mapper.map(savedPlane, PlaneDto.class);
    }

    @Transactional
    public PlaneDto updatePlane(Long planeId,
                                UpdatePlaneDto updatePlaneDto) {
        Plane plane = getPlaneEntity(planeId);
        mapper.map(updatePlaneDto, plane);
        return mapper.map(plane, PlaneDto.class);
    }

    @Transactional
    public void deletePlane(Long planeId) {
        Plane plane = getPlaneEntity(planeId);
        plane.setDeleted(true);
        ticketRepository.findTicketByPlane_Id(planeId).forEach(it -> it.setDeleted(true));
    }

    @Transactional
    public List<PlaneDto> getPlaneInAir() {
        return planeRepository.findPlaneInAir()
                .stream()
                .map(it -> mapper.map(it, PlaneDto.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public List<PlaneDto> getPlane10MinutesDown() {
        return planeRepository.findPlane10MinutesDown()
                .stream()
                .map(it -> mapper.map(it, PlaneDto.class))
                .collect(Collectors.toList());
    }

    private Plane getPlaneEntity(Long planeId) {
        return planeRepository
                .findById(planeId)
                .orElseThrow(() -> new DataNotFoundException("plane with id " + planeId + " does not exists"));
    }
}