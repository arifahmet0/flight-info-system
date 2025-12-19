package com.cmpe331.flight_roster.service;

import com.cmpe331.flight_roster.entity.Passenger;
import com.cmpe331.flight_roster.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    // Kırmızı hatayı çözen kısım:
    public List<Passenger> getPassengersForFlight(String flightNumber) {
        return passengerRepository.findByFlight_FlightNumber(flightNumber);
    }

    public List<Passenger> findPassengersByFlightNumber(String flightNumber) {
        return passengerRepository.findByFlight_FlightNumber(flightNumber);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}