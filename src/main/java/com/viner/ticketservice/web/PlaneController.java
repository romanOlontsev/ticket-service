package com.viner.ticketservice.web;

import com.viner.ticketservice.dto.AddOrUpdatePlaneDto;
import com.viner.ticketservice.dto.PlaneDto;
import com.viner.ticketservice.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;

    @GetMapping
    public List<PlaneDto> getPlanes() {
        return planeService.getPlanes();
    }

    @GetMapping("/{planeId}")
    public PlaneDto getPlane(@PathVariable Long planeId) {
        return planeService.getPlaneById(planeId);
    }

    @PostMapping
    public PlaneDto addPlane(@RequestBody AddOrUpdatePlaneDto plane) {
        return planeService.addPlane(plane);
    }

    @PutMapping("/{planeId}")
    public PlaneDto updatePlane(@PathVariable Long planeId, @RequestBody AddOrUpdatePlaneDto plane) {
        return planeService.updatePlane(planeId, plane);
    }

    @PatchMapping("/{planeId}")
    public void markAsDeletePlane(@PathVariable Long planeId) {
        planeService.markAsDeletedPlane(planeId);
    }

    @DeleteMapping("/{planeId}")
    public void deletePlane(@PathVariable Long planeId) {
        planeService.deletePlane(planeId);
    }
}
