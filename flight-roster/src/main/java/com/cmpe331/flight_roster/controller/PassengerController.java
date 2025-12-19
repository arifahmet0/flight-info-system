package com.cmpe331.flight_roster.controller;

import com.cmpe331.flight_roster.entity.Passenger;
import com.cmpe331.flight_roster.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // Bir uçuşun yolcularını getir: localhost:8080/api/passengers/AA1234
    @GetMapping("/{flightNumber}")
    public List<Passenger> getPassengersByFlight(@PathVariable String flightNumber) {
        return passengerService.getPassengersForFlight(flightNumber);
    }
}