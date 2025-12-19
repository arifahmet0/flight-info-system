package com.cmpe331.flight_roster.service;

import com.cmpe331.flight_roster.entity.Attendant;
import com.cmpe331.flight_roster.repository.AttendantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendantService {

    private final AttendantRepository attendantRepository;

    // Kırmızı hatayı çözen kısım:
    public List<Attendant> getAllAttendants() {
        return attendantRepository.findAll();
    }

    public List<Attendant> findAttendantsByFlightNumber(String flightNumber) {
        return attendantRepository.findByFlight_FlightNumber(flightNumber);
    }
}