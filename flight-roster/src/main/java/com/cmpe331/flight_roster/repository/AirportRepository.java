package com.cmpe331.flight_roster.repository;

import com.cmpe331.flight_roster.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
    // String: Çünkü ID'miz (Code) bir String (IST, ESB)
}