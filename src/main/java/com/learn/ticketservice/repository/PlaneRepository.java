package com.learn.ticketservice.repository;

import com.learn.ticketservice.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    @Query(value = "SELECT p FROM Plane p WHERE p.depart> :current_date")
    Page<Plane> findPlanesByDepartAfterCurrentDate(@Param("current_date") LocalDateTime currentDate, Pageable pageable);

    Optional<Plane> findPlaneByName(String name);


    @Query(value = "SELECT p FROM Plane p " +
            "WHERE DATEADD(SECOND, p.duration/1000000000, p.depart) > CURRENT_TIMESTAMP " +
            "AND DEPART < CURRENT_TIMESTAMP")
    List<Plane> findPlaneInAir();

    @Query(value = "SELECT p FROM Plane p " +
            "WHERE DATEADD(MINUTE, -(10), DATEADD(SECOND, p.duration/1000000000, p.depart)) <= CURRENT_TIMESTAMP " +
            "AND DATEADD(SECOND, duration/1000000000, depart) > CURRENT_TIMESTAMP")
    List<Plane> findPlane10MinutesDown();
}
