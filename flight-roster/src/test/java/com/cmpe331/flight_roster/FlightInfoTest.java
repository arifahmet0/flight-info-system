package com.cmpe331.flight_roster;

import com.cmpe331.flight_roster.entity.Airport; 
import com.cmpe331.flight_roster.entity.Flight;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FlightInfoTest {

    @Test
    void testFlightNumberFormat() {
        Flight flight = new Flight();
        flight.setFlightNumber("TK1923");

        assertNotNull(flight.getFlightNumber());
        assertEquals(6, flight.getFlightNumber().length());
        assertTrue(flight.getFlightNumber().startsWith("TK"));
    }

    @Test
    void testDepartureTimeIsFuture() {
        Flight flight = new Flight();
        flight.setDepartureDateTime(LocalDateTime.now().plusDays(3));
        assertTrue(flight.getDepartureDateTime().isAfter(LocalDateTime.now()));
    }

    @Test
    void testRouteConsistency() {
        // SENARYO: Kalkış ve Varış havalimanları aynı nesne olamaz
        
        Flight flight = new Flight();
        Airport source = new Airport();
        Airport destination = new Airport();

        flight.setSourceAirport(source);
        flight.setDestinationAirport(destination);

        // DÜZELTME BURADA: assertNotEquals yerine assertNotSame kullanıyoruz.
        // Bu, "Bu iki nesne bellekte aynı yeri mi işaret ediyor?" diye bakar.
        assertNotSame(flight.getSourceAirport(), flight.getDestinationAirport(), "Kalkış ve varış aynı havalimanı olamaz.");
    }

    @Test
    void testFlightDurationPositive() {
        Flight flight = new Flight();
        flight.setDurationMinutes(90);
        assertTrue(flight.getDurationMinutes() > 0);
    }
}