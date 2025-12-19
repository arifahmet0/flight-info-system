package com.cmpe331.flight_roster.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.cmpe331.flight_roster.entity.Flight;
import com.cmpe331.flight_roster.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightService flightService;

    // Constructor (Yapıcı Metot)
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Tüm uçuşları getir: localhost:8080/api/flights
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    // Belirli bir uçuşu getir: localhost:8080/api/flights/AA1234
    @GetMapping("/{flightNumber}")
    public Flight getFlight(@PathVariable String flightNumber) {
        return flightService.getFlightByNumber(flightNumber);
    }
}