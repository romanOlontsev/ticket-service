package com.learn.ticketservice.web;

import com.learn.ticketservice.service.PlaneService;
import com.learn.ticketservice.service.dto.PlaneDto;
import com.learn.ticketservice.web.annotation.DefaultPageableParameters;
import com.learn.ticketservice.web.dto.AddPlaneDto;
import com.learn.ticketservice.web.dto.UpdatePlaneDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/{planeId}")
    public PlaneDto getPlane(@PathVariable("planeId") Long id) {
        return planeService.getPlaneById(id);
    }

    @GetMapping
    public Page<PlaneDto> getPlanes(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime localDateTime,
            @DefaultPageableParameters @Parameter(hidden = true) Pageable pageable) {
        return planeService.getPlanes(localDateTime, pageable);
    }

    @GetMapping("/air")
    public List<PlaneDto> getPlanesInAir() {
        return planeService.getPlaneInAir();
    }

    @GetMapping("/down")
    public List<PlaneDto> getPlanes10MinutesDown() {
        return planeService.getPlane10MinutesDown();
    }

    @PostMapping
    public PlaneDto addPlane(@RequestBody @Valid AddPlaneDto plane) {
        return planeService.addNewPlane(plane);
    }

    @PutMapping("/{planeId}")
    public PlaneDto updatePlane(@PathVariable("planeId") Long id,
                                @RequestBody @Valid UpdatePlaneDto updatePlaneDto) {
        return planeService.updatePlane(id, updatePlaneDto);
    }

    @PatchMapping("/{planeId}")
    public void deletePlane(@PathVariable("planeId") Long planeId) {
        planeService.deletePlane(planeId);
    }
}

