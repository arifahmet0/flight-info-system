package com.cmpe331.flight_roster.controller;

import com.cmpe331.flight_roster.entity.*;
import com.cmpe331.flight_roster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Frontend ile bağlantı izni
public class RosterApiController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService; // Eğer PilotService yoksa repository'i direkt @Autowired yapabilirsin

    @Autowired
    private AttendantService attendantService;

    @Autowired
    private PassengerService passengerService;

    // Frontend'in çağırdığı o meşhur fonksiyon bu:
    @GetMapping("/roster")
    public ResponseEntity<?> getFlightRoster(@RequestParam String flightNumber,
                                             @RequestParam(defaultValue = "SQL") String dbType) {

        // 1. Uçuş Bilgisini Bul
        Flight flight = flightService.getFlightByNumber(flightNumber);

        if (flight == null) {
            return ResponseEntity.badRequest().body("Flight not found!");
        }

        // 2. O uçuşa ait Pilotları Bul
        // Not: Eğer service'inde bu metod yoksa repository.findByFlight_FlightNumber(flightNumber) kullanabilirsin.
        List<Pilot> pilots = pilotService.findPilotsByFlightNumber(flightNumber);

        // 3. O uçuşa ait Kabin Ekibini Bul
        List<Attendant> attendants = attendantService.findAttendantsByFlightNumber(flightNumber);

        // 4. O uçuşa ait Yolcuları Bul
        List<Passenger> passengers = passengerService.findPassengersByFlightNumber(flightNumber);

        // 5. Hepsini paketle ve gönder
        Map<String, Object> rosterResponse = new HashMap<>();
        rosterResponse.put("flightInfo", flight);
        rosterResponse.put("pilots", pilots);
        rosterResponse.put("attendants", attendants);
        rosterResponse.put("passengers", passengers);

        return ResponseEntity.ok(rosterResponse);
    }
}