package com.cmpe331.flight_roster; // Paket ismine dikkat

import com.cmpe331.flight_roster.entity.Flight;
import com.cmpe331.flight_roster.entity.Pilot;
import com.cmpe331.flight_roster.entity.VehicleType;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightDeckTest {

    @Test
    void testValidPilotPairing() {
        // SENARYO: 1 Senior ve 1 Junior Pilot (Valid)
        Flight flight = new Flight();
        flight.setFlightNumber("TK1923");
        
        Pilot p1 = new Pilot(); p1.setSeniorityLevel("Senior");
        Pilot p2 = new Pilot(); p2.setSeniorityLevel("Junior");

        List<Pilot> pilots = new ArrayList<>();
        pilots.add(p1);
        pilots.add(p2);
        flight.setPilots(pilots);

        // Kural Kontrolü
        boolean hasSenior = flight.getPilots().stream()
                .anyMatch(p -> "Senior".equals(p.getSeniorityLevel()));

        assertTrue(hasSenior, "En az bir Senior pilot bulunmalıdır.");
    }

    @Test
    void testInvalidSeniority() {
        // SENARYO: Sadece Junior'lar uçamaz
        Flight flight = new Flight();
        List<Pilot> pilots = new ArrayList<>();

        Pilot p1 = new Pilot(); p1.setSeniorityLevel("Junior");
        Pilot p2 = new Pilot(); p2.setSeniorityLevel("Junior");

        pilots.add(p1);
        pilots.add(p2);
        flight.setPilots(pilots);

        boolean hasSenior = flight.getPilots().stream()
                .anyMatch(p -> "Senior".equals(p.getSeniorityLevel()));

        assertFalse(hasSenior, "Sadece Junior pilotlardan oluşan ekip geçersizdir.");
    }

    @Test
    void testVehicleTypeMismatch() {
        // SENARYO: Uçak tipi uyuşmazlığı
        
        // 1. Boeing Tipi Oluştur
        VehicleType boeing = new VehicleType();
        // İsim vermesek bile bellekteki yeri farklıdır
        
        // 2. Airbus Tipi Oluştur
        VehicleType airbus = new VehicleType();

        // 3. Atamaları Yap
        Flight flight = new Flight();
        flight.setVehicleType(boeing); // Uçak Boeing

        Pilot pilot = new Pilot();
        pilot.setAllowedVehicleType(airbus); // Pilot Airbus kullanabilir

        // DÜZELTME BURADA: .equals() yerine "referans kontrolü" yapıyoruz veya mantığı düzeltiyoruz
        // Farklı "new" ile oluşturuldukları için birbirlerine eşit değildirler.
        boolean isCompatible = (pilot.getAllowedVehicleType() == flight.getVehicleType());

        assertFalse(isCompatible, "Boeing uçağını Airbus sertifikalı pilot uçuramaz.");
    }
}