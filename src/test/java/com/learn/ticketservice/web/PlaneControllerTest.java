package com.learn.ticketservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.learn.ticketservice.model.Plane;
import com.learn.ticketservice.repository.PlaneRepository;
import com.learn.ticketservice.web.dto.AddPlaneDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaneRepository planeRepository;

    private List<Plane> planeList;

    @BeforeEach
    private void init() {
        Plane plane1 = Plane.builder()
                .id(1L)
                .depart(LocalDateTime.of(2022, Month.MAY, 23, 23, 5))
                .duration(Duration.ofHours(5))
                .from("Moscow")
                .to("Nizhnii Novgorod")
                .name("Air")
                .places(10)
                .isDeleted(false)
                .tickets(List.of())
                .build();
        Plane plane2 = Plane.builder()
                .id(2L)
                .depart(LocalDateTime.of(2022, Month.JUNE, 23, 23, 5))
                .duration(Duration.ofHours(2))
                .from("Moscow1")
                .to("Nizhnii Novgorod2")
                .name("AirR")
                .places(11)
                .isDeleted(false)
                .tickets(List.of())
                .build();
        Plane plane3 = Plane.builder()
                .id(3L)
                .depart(LocalDateTime.of(2022, Month.JULY, 23, 23, 5))
                .duration(Duration.ofHours(3))
                .from("Mos")
                .to("Novgorod")
                .name("AiG")
                .places(12)
                .isDeleted(false)
                .tickets(List.of())
                .build();

        planeList = List.of(plane1, plane2, plane3);
    }

    @Test
    void getPlaneTest() throws Exception {
        Plane plane = planeList.get(0);

        when(planeRepository.findById(anyLong())).thenReturn(Optional.of(plane));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/planes/1");
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
//                .andExpect(jsonPath("$.tickets[0].id").value(0));

        verify(planeRepository, times(1)).findById(any());
    }

    @Test
    void getPlanes() throws Exception {
        List<Plane> planes = planeList;
//        Pageable pageable = PageRequest.of(0, 10);
//        LocalDateTime dateTime = LocalDateTime.now();

        when(planeRepository.findPlanesByDepartAfterCurrentDate(any(LocalDateTime.class), any()))
                .thenReturn(new PageImpl<>(planes));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/planes")
                .param("localDateTime",
                        LocalDateTime.now().plusMinutes(2L)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .param("page", "0")
                .param("size", "10");
        mockMvc.perform(builder)
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id").value(0));
//                .andExpect(jsonPath("$.tickets[0].id").value(0));

    }

    @Test
    void addPlane() throws Exception {
        AddPlaneDto plane = AddPlaneDto.builder()
                .depart(LocalDateTime.of(2022, Month.MAY, 23, 23, 5))
                .duration(Duration.ofHours(5))
                .from("Moscow")
                .to("Nizhnii Novgorod")
                .name("Air")
                .places(10)
                .build();
//        Map<String, Object> body = new HashMap<>();
//        body.put("id", 0);
//        body.put("depart", LocalDateTime.of(2022, Month.MAY, 23, 23, 5));
//        body.put("duration", Duration.ofHours(5));
//        body.put("from", "Moscow");
//        body.put("to", "Nizhnii Novgorod");
//        body.put("name", "Air");
//        body.put("places", 10);

        when(planeRepository.findPlaneByName(anyString())).thenReturn(Optional.empty());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/planes");

        mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(plane))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id").value(0));
//                .andExpect(jsonPath("$.tickets[0].id").value(0));

        verify(planeRepository, times(1)).findById(any());

    }

    @Test
    void updatePlane() {
    }

    @Test
    void deletePlane() {
    }

    @Test
    void getPlanesInAir() {
    }

    @Test
    void getPlanes10MinutesDown() {
    }

}