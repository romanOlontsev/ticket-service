package com.learn.ticketservice.service;

import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.model.Ticket;
import com.learn.ticketservice.model.User;
import com.learn.ticketservice.repository.TicketRepository;
import com.learn.ticketservice.service.dto.TicketDto;
import com.learn.ticketservice.web.dto.UpdateTicketDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserService userService;

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Spy
    private final MapperFacade mapper = mapperFactory.getMapperFacade();

    @Test
    void contextLoads() {
    }

    private List<Ticket> ticketList;

    @BeforeEach
    private void init() {
        Ticket ticket1 = Ticket.builder()
                .id(1L)
                .isDeleted(false)
                .price(BigDecimal.ONE)
                .plane(null)
                .user(null)
                .build();

        Ticket ticket2 = Ticket.builder()
                .id(2L)
                .isDeleted(true)
                .price(BigDecimal.ZERO)
                .plane(null)
                .user(null)
                .build();

        ticketList = List.of(ticket1, ticket2);
    }

    @Test
    void getTicketByIdTest() {
        Ticket ticket = ticketList.get(0);

        when(ticketRepository.findTicketByIdAndPlane_Id(anyLong(), anyLong()))
                .thenReturn(Optional.of(ticket));
        TicketDto serviceTicketById = ticketService.getTicketById(1L, 1L);

        assertThat(serviceTicketById).isNotNull().extracting("price").isEqualTo(BigDecimal.ONE);
        assertThat(serviceTicketById).isEqualTo(mapper.map(ticket, TicketDto.class));
    }

    @Test
    void getTicketByIdTest_shouldThrowException() {
        when(ticketRepository.findTicketByIdAndPlane_Id(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> ticketService.getTicketById(1L, 1L))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    void getTicketsTest_isSoldEqualsNull() {
        Ticket ticket1 = ticketList.get(0);
        Ticket ticket2 = ticketList.get(1);

        when(ticketRepository.findTicketByPlane_Id(anyLong()))
                .thenReturn(List.of(ticket1, ticket2));
        List<TicketDto> serviceGetTickets = ticketService.getTickets(1L, null);

        assertThat(serviceGetTickets.get(0)).isNotNull().extracting("price").isEqualTo(BigDecimal.ONE);
        assertThat(serviceGetTickets.get(1)).isNotNull().extracting("isDeleted").isEqualTo(true);
    }

    @Test
    void getTicketsTest_isSoldEqualsTrue() {
        Ticket ticket1 = ticketList.get(0);

        when(ticketRepository.findTicketByPlane_IdAndUserNotNull(anyLong()))
                .thenReturn(List.of(ticket1));
        List<TicketDto> serviceGetTickets = ticketService.getTickets(1L, true);

        assertThat(serviceGetTickets.get(0)).isNotNull().extracting("price").isEqualTo(BigDecimal.ONE);
        assertThat(serviceGetTickets).containsOnly(mapper.map(ticket1, TicketDto.class));
    }

    @Test
    void getTicketsTest_isSoldEqualsFalse() {
        Ticket ticket2 = ticketList.get(1);

        when(ticketRepository.findTicketByPlane_IdAndUserIsNull(anyLong()))
                .thenReturn(List.of(ticket2));
        List<TicketDto> serviceGetTickets = ticketService.getTickets(1L, false);

        assertThat(serviceGetTickets.get(0)).isNotNull().extracting("price").isEqualTo(BigDecimal.ZERO);
        assertThat(serviceGetTickets).containsOnlyOnce(mapper.map(ticket2, TicketDto.class));
    }

    @Test
    void updateTicketTest() {
        User user = User.builder()
                .id(11L)
                .name("Alfredo")
                .tickets(new ArrayList<>())
                .build();
        UpdateTicketDto updateTicketDto = UpdateTicketDto.builder()
                .price(BigDecimal.TEN)
                .build();
        Ticket ticket = ticketList.get(0);

        when(ticketRepository.findTicketByIdAndPlane_Id(anyLong(), anyLong()))
                .thenReturn(Optional.of(ticket));
        when(userService.getUserEntity(any()))
                .thenReturn(user);
        TicketDto updateTicket = ticketService.updateTicket(1L, 1L, updateTicketDto);

        assertThat(updateTicket.getUser()).extracting("id").isEqualTo(11L);
        assertThat(updateTicket).extracting("price").isEqualTo(BigDecimal.TEN);
    }

    @Test
    void deleteTicket() {
        Ticket ticket = ticketList.get(0);

        when(ticketRepository.findTicketByIdAndPlane_Id(anyLong(), anyLong()))
                .thenReturn(Optional.of(ticket));
        ticketService.deleteTicket(1L, 1L);

        assertThat(ticket).extracting("isDeleted").isEqualTo(true);
    }
}
