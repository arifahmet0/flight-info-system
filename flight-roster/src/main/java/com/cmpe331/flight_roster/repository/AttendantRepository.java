package com.cmpe331.flight_roster.repository;

import com.cmpe331.flight_roster.entity.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendantRepository extends JpaRepository<Attendant, Integer> {
    // RosterController için gerekli metod:
    List<Attendant> findByFlight_FlightNumber(String flightNumber);
}