package com.cmpe331.flight_roster.repository;

import com.cmpe331.flight_roster.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
    // ID tipi String (Flight Number: AA1234)
}