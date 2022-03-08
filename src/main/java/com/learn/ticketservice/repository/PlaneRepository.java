package com.learn.ticketservice.repository;

import com.learn.ticketservice.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
