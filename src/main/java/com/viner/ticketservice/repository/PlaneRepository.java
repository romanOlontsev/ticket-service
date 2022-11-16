package com.viner.ticketservice.repository;

import com.viner.ticketservice.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    Optional<Plane> findByFlightNumber(Integer flightNumber);
}
