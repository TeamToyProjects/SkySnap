package com.toyproject.skysnap.airline.repository;

import com.toyproject.skysnap.airline.entitiy.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByCode(String code);
}
