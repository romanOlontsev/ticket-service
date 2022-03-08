package com.learn.ticketservice.web;

import com.learn.ticketservice.service.PlaneService;
import com.learn.ticketservice.service.dto.PlaneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/{id}")
    public PlaneDto plane(@PathVariable("id") Long id) {
        return planeService.getPlaneById(id);
    }

}

