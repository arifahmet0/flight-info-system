package com.cmpe331.flight_roster.repository;


import com.cmpe331.flight_roster.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    List<Passenger> findByFlight_FlightNumber(String flightNumber);
}