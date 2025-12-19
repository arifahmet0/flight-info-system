package com.cmpe331.flight_roster.service;

import com.cmpe331.flight_roster.entity.Flight;
import com.cmpe331.flight_roster.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    // Tüm uçuşları getir
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Tek bir uçuşu ID ile getir
    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findById(flightNumber)
                .orElseThrow(() -> new RuntimeException("Uçuş bulunamadı: " + flightNumber));
    }
}