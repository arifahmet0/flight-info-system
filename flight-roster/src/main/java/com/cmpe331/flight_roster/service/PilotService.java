package com.cmpe331.flight_roster.service;

import com.cmpe331.flight_roster.entity.Pilot;
import com.cmpe331.flight_roster.repository.PilotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotService {

    private final PilotRepository pilotRepository;

    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    // Roster için:
    public List<Pilot> findPilotsByFlightNumber(String flightNumber) {
        return pilotRepository.findByFlight_FlightNumber(flightNumber);
    }
}