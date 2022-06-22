package com.learn.ticketservice.repository;

import com.learn.ticketservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPassport(String passport);
}
