package com.learn.ticketservice.service;

import com.learn.ticketservice.repository.PlaneRepository;
import com.learn.ticketservice.service.dto.PlaneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneDto getPlaneById(Long id) {
        return new PlaneDto(planeRepository.findById(id).get().getId());
    }
}
