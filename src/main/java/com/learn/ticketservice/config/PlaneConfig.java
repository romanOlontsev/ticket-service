//package com.learn.ticketservice.config;
//
//import com.learn.ticketservice.model.Plane;
//import com.learn.ticketservice.model.Ticket;
//import com.learn.ticketservice.model.User;
//import com.learn.ticketservice.repository.PlaneRepository;
//import com.learn.ticketservice.repository.TicketRepository;
//import com.learn.ticketservice.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class PlaneConfig {
//
//    private final PlaneRepository planeRepository;
//    private final UserRepository userRepository;
//    private final TicketRepository ticketRepository;
//
//    @PostConstruct
//    public void init() {
//        Ticket ticket1 = Ticket.builder()
//                .price(BigDecimal.ONE)
//                .isDeleted(false)
//                .build();
//        Ticket ticket2 = Ticket.builder()
//                .price(BigDecimal.valueOf(12L))
//                .isDeleted(false)
//                .build();
//        Ticket ticket3 = Ticket.builder()
//                .price(BigDecimal.valueOf(9L))
//                .isDeleted(false)
//                .build();
//        Ticket ticket4 = Ticket.builder()
//                .price(BigDecimal.valueOf(2.2))
//                .isDeleted(false)
//                .build();
//        ticketRepository.saveAll(List.of(ticket1, ticket2, ticket3, ticket4));
//
//        User user1 = User.builder()
//                .name("Alesha")
//                .lastname("Jukov")
//                .passport("RF1001")
//                .isDeleted(false)
//                .build();
//        User user2 = User.builder()
//                .name("Andrew")
//                .lastname("Kvasov")
//                .passport("RF201")
//                .isDeleted(false)
//                .build();
//        User user3 = User.builder()
//                .name("Nadia")
//                .lastname("Pushka")
//                .passport("RF404")
//                .isDeleted(false)
//                .build();
//        userRepository.saveAll(List.of(user1, user2, user3));
//
//        Plane first = Plane.builder()
//                .depart(LocalDateTime.of(2022, Month.MAY, 23, 23,5))
//                .duration(Duration.ofHours(5))
//                .from("Moscow")
//                .to("Nizhnii Novgorod")
//                .name("Air")
//                .places(500)
//                .isDeleted(false)
//                .build();
//        Plane second = Plane.builder()
//                .depart(LocalDateTime.of(2022, Month.APRIL, 16, 14,40))
//                .duration(Duration.ofHours(1))
//                .from("Nizhnii Novgorod")
//                .to("Moscow")
//                .name("AirLine")
//                .places(404)
//                .isDeleted(false)
//                .build();
//
//
//        planeRepository.saveAll(List.of(first, second));
//
//        ticket1.setPlane(first);
//        ticket2.setPlane(second);
//        ticket3.setPlane(second);
//        ticket4.setPlane(first);
//
//        ticket1.setUser(user1);
//        ticket2.setUser(user1);
//        ticket3.setUser(user2);
//        ticket4.setUser(user3);
//
//        ticketRepository.saveAll(List.of(ticket1, ticket2, ticket3, ticket4));
//
//        user1.setTickets(List.of(ticket1, ticket2));
//        user2.setTickets(List.of(ticket3));
//        user3.setTickets(List.of(ticket4));
//
//        userRepository.saveAll(List.of(user1, user2, user3));
//
//        first.setTickets(List.of(ticket1, ticket4));
//        second.setTickets(List.of(ticket2, ticket3));
//
//        planeRepository.saveAll(List.of(first, second));
//
//    }
//
//
////    @Bean
////    CommandLineRunner commandLineRunner(PlaneRepository planeRepository) {
////        return args -> {
////
////        };
////
////    }
//}
