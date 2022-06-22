package com.learn.ticketservice.service;

import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.exception.EntityExistsException;
import com.learn.ticketservice.model.Plane;
import com.learn.ticketservice.model.Ticket;
import com.learn.ticketservice.repository.PlaneRepository;
import com.learn.ticketservice.repository.TicketRepository;
import com.learn.ticketservice.service.dto.PlaneDto;
import com.learn.ticketservice.web.dto.AddPlaneDto;
import com.learn.ticketservice.web.dto.UpdatePlaneDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaneServiceTest {

    @InjectMocks
    private PlaneService service;

    @Mock
    private PlaneRepository planeRepository;

    @Mock
    private TicketRepository ticketRepository;

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Spy
    private final MapperFacade mapper = mapperFactory.getMapperFacade();

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void addNewPlane() {
        //given
        AddPlaneDto plane = AddPlaneDto.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .build();

        Plane savePlane = Plane.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .build();

        //when
        when(planeRepository.save(any())).thenReturn(savePlane);
        PlaneDto planeDto = service.addNewPlane(plane);
        //then
        assertThat(planeDto).isNotNull().extracting("places").isEqualTo(1);
        assertThat(planeDto).extracting("to").isEqualTo("NiN");
        assertThat(planeDto).extracting("duration").isInstanceOf(Duration.class);
    }

    @Test
    void addNewPlane_shouldThrowException() {
        AddPlaneDto plane = AddPlaneDto.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .build();

        Plane savePlane = Plane.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .build();

        when(planeRepository.findPlaneByName(any())).thenReturn(Optional.of(savePlane));

        assertThatThrownBy(() -> service.addNewPlane(plane)).isInstanceOf(EntityExistsException.class);
    }

    @Test
    void getPlaneById() {
        //given
        Plane plane = Plane.builder()
                .id(14L)
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .tickets(null)
                .places(1)
                .isDeleted(false)
                .build();

//        PlaneDto planeDto = new PlaneDto(
//                14,
//                "Air",
//                1,
//                LocalDateTime.of(2022, 3, 24, 12, 0),
//                Duration.ZERO,
//                "Mos",
//                "NiN",
//                null,
//                false);

        //when
        doReturn(Optional.of(plane)).when(planeRepository).findById(14L);

        PlaneDto planeById = service.getPlaneById(14L);
        //then
        assertThat(planeById).isNotNull().isEqualTo(mapper.map(plane, PlaneDto.class));
        assertThat(planeById).extracting("tickets").isEqualTo(null);

    }

    @Test
    void getPlaneById_shouldThrowException() {
        Plane plane = Plane.builder()
                .id(14L)
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .tickets(null)
                .places(1)
                .isDeleted(false)
                .build();

        when(planeRepository.findById(14L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getPlaneById(14L)).isInstanceOf(DataNotFoundException.class);
    }

    @Test
    void getPlanes() {
        Plane plane1 = Plane.builder()
                .name("Air1")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .isDeleted(false)
                .build();
        Plane plane2 = Plane.builder()
                .name("Air2")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 25, 12, 5))
                .duration(Duration.ofHours(1))
                .places(1)
                .isDeleted(false)
                .build();
        Plane plane3 = Plane.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 26, 14, 10))
                .duration(Duration.ofHours(2))
                .places(1)
                .isDeleted(false)
                .build();
        Pageable pageable = PageRequest.of(0, 10);

        when(planeRepository.findPlanesByDepartAfterCurrentDate(any(), any()))
                .thenReturn(new PageImpl<>(List.of(plane1, plane2, plane3)));
        Page<PlaneDto> page = service.getPlanes(LocalDateTime.now(), pageable);

        assertThat(page).isNotNull();
        assertThat(page.getContent().get(2)).extracting("name").isEqualTo("Air");
        assertThat(page.getContent().get(1)).extracting("duration").isEqualTo(Duration.ofHours(1));
        assertThat(page.getContent().get(0)).extracting("depart").isInstanceOf(LocalDateTime.class);

    }

    @Test
    void updatePlane() {
        UpdatePlaneDto updatePlaneDto = UpdatePlaneDto.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .build();

        Plane plane = Plane.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .build();

        when(planeRepository.findById(any())).thenReturn(Optional.of(plane));
        PlaneDto updatePlane = service.updatePlane(0L, updatePlaneDto);

        assertThat(updatePlane).isNotNull();
        assertThat(updatePlane).extracting("name").isEqualTo("Air");
        assertThat(updatePlane).extracting("depart").isInstanceOf(LocalDateTime.class);
    }

    @Test
    void deletePlane() {
        Ticket ticket1 = Ticket.builder()
                .price(BigDecimal.ONE)
                .isDeleted(false)
                .build();
        Ticket ticket2 = Ticket.builder()
                .price(BigDecimal.valueOf(12L))
                .isDeleted(false)
                .build();
        Plane plane = Plane.builder()
                .name("Air")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.of(2022, 3, 24, 12, 0))
                .duration(Duration.ZERO)
                .places(1)
                .tickets(List.of(ticket1, ticket2))
                .build();

        when(planeRepository.findById(any())).thenReturn(Optional.of(plane));
        when(ticketRepository.findTicketByPlane_Id(any())).thenReturn(List.of(ticket1, ticket2));

        service.deletePlane(0L);
        assertThat(plane).extracting("isDeleted").isEqualTo(true);
        assertThat(plane.getTickets().get(0)).extracting("isDeleted").isEqualTo(true);
        assertThat(plane.getTickets().get(1)).extracting("isDeleted").isEqualTo(true);
    }

    @Test
    void getPlaneInAir() {
        Plane plane1 = Plane.builder()
                .name("Air1")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.now().minusMinutes(2))
                .duration(Duration.ofHours(2))
                .build();
        Plane plane2 = Plane.builder()
                .name("Air2")
                .to("Mos")
                .from("NiN")
                .depart(LocalDateTime.now().minusMinutes(3))
                .duration(Duration.ofHours(3))
                .build();

        when(planeRepository.findPlaneInAir()).thenReturn(List.of(plane1, plane2));

        List<PlaneDto> planeInAir = service.getPlaneInAir();
        assertThat(planeInAir).isNotNull();
        assertThat(planeInAir.get(0)).extracting("depart").isNotNull();
        assertThat(planeInAir.get(1)).extracting("duration").isNotNull();
    }

    @Test
    void getPlane10MinutesDown() {
        Plane plane1 = Plane.builder()
                .name("Air1")
                .to("NiN")
                .from("Mos")
                .depart(LocalDateTime.now().minusMinutes(2))
                .duration(Duration.ofHours(2))
                .build();
        Plane plane2 = Plane.builder()
                .name("Air2")
                .to("Mos")
                .from("NiN")
                .depart(LocalDateTime.now().minusMinutes(3))
                .duration(Duration.ofHours(3))
                .build();

        when(planeRepository.findPlane10MinutesDown()).thenReturn(List.of(plane1, plane2));

        List<PlaneDto> planeInAir = service.getPlane10MinutesDown();
        assertThat(planeInAir).isNotNull();
        assertThat(planeInAir.get(0)).extracting("depart").isNotNull();
        assertThat(planeInAir.get(1)).extracting("duration").isNotNull();
    }
}