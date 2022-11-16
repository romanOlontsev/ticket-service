package com.viner.ticketservice.service;

import com.viner.ticketservice.dto.AddOrUpdatePlaneDto;
import com.viner.ticketservice.dto.PlaneDto;
import com.viner.ticketservice.entity.Plane;
import com.viner.ticketservice.entity.Ticket;
import com.viner.ticketservice.exceptions.DataNotFoundException;
import com.viner.ticketservice.exceptions.EntityExistsException;
import com.viner.ticketservice.mappers.PlaneMapper;
import com.viner.ticketservice.repository.PlaneRepository;
import com.viner.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;
    private final PlaneMapper mapper;

    @Transactional
    public List<PlaneDto> getPlanes() {
        return planeRepository.findAll()
                              .stream()
                              .map(mapper::planeToPlaneDto)
                              .collect(Collectors.toList());
    }

    @Transactional
    public PlaneDto getPlaneById(Long planeId) {
        return mapper.planeToPlaneDto(getPlaneEntity(planeId));
    }

    @Transactional
    public PlaneDto addPlane(AddOrUpdatePlaneDto addPlane) {
        planeRepository.findByFlightNumber(addPlane.getFlightNumber())
                       .ifPresent(it -> {
                           throw new EntityExistsException("Flight with number=" + addPlane.getFlightNumber()
                                   + " already exists");
                       });
        Plane planeToGenerate = mapper.addOrUpdatePlaneDtoToPlane(addPlane);
        planeToGenerate.addTicketsToPlane(addPlane.getTicketPrice(), addPlane.getPlaces());
        Plane savedPlane = planeRepository.save(planeToGenerate);
        return mapper.planeToPlaneDto(savedPlane);
    }

    @Transactional
    public PlaneDto updatePlane(Long planeId, AddOrUpdatePlaneDto plane) {
        Plane foundPlane = getPlaneEntity(planeId);
        long countSoldPlaces = foundPlane.getTickets()
                                         .stream()
                                         .filter(it -> it.getUser() != null)
                                         .count();
        if (countSoldPlaces > plane.getPlaces()) {
            throw new ArithmeticException(
                    "The update is not possible. The requested number of seats is less than those sold.");
        }
        List<Ticket> listPassenger = foundPlane.getTickets()
                                               .stream()
                                               .filter(it -> it.getUser() != null)
                                               .collect(Collectors.toList());
        foundPlane.getTickets()
                  .stream()
                  .filter(it -> it.getUser() == null)
                  .forEach(ticketRepository::delete);
        foundPlane.setTickets(listPassenger);
        foundPlane.addTicketsToPlane(plane.getTicketPrice(), plane.getPlaces());

        mapper.updatePlaneFromAddOrUpdatePlaneDto(plane, foundPlane);
        Plane savedPlane = planeRepository.save(foundPlane);
        return mapper.planeToPlaneDto(savedPlane);
    }

    @Transactional
    public void markAsDeletedPlane(Long planeId) {
        Plane foundPlane = getPlaneEntity(planeId);
        foundPlane.setDeleted(true);
        foundPlane.getTickets()
                  .forEach(it -> it.setDeleted(true));
    }

    @Transactional
    public void deletePlane(Long planeId) {
        Plane foundPlane = getPlaneEntity(planeId);
        foundPlane.getTickets()
                  .stream()
                  .filter(it -> it.getUser() != null)
                  .forEach(it -> it.getUser()
                                   .removeTicketFromUser(it));
        planeRepository.delete(foundPlane);
    }

    private Plane getPlaneEntity(Long planeId) {
        return planeRepository.findById(planeId)
                              .orElseThrow(() -> new DataNotFoundException("Plane with id=" + planeId + " not found"));
    }
}