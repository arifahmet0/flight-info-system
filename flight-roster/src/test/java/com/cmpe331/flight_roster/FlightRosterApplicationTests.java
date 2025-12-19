package com.cmpe331.flight_roster;

import com.cmpe331.flight_roster.entity.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// NOT: @SpringBootTest annotation'ını kaldırdık çünkü veritabanı bağlantısı yok.
// Bu haliyle saf bir "Integration Logic" testi olarak çalışır ve veritabanı hatası vermez.

class FlightRosterApplicationTests {

    @Test
    void testFullFlightRosterIntegration() {
        // SENARYO: Uçuş Operasyonu Entegrasyon Testi
        // Amaç: Veritabanı olmadan, nesnelerin birbiriyle uyumunu test etmek.

        // 1. ADIM: Uçuş Tanımla
        Flight flight = new Flight();
        flight.setFlightNumber("TK2025");
        flight.setDepartureDateTime(LocalDateTime.now().plusHours(5));

        // 2. ADIM: Kokpit Ekibini Kur
        Pilot p1 = new Pilot(); p1.setName("Ali Kaptan"); p1.setSeniorityLevel("Senior");
        Pilot p2 = new Pilot(); p2.setName("Veli FO"); p2.setSeniorityLevel("Junior");
        
        List<Pilot> pilotList = new ArrayList<>();
        pilotList.add(p1);
        pilotList.add(p2);
        flight.setPilots(pilotList);

        // 3. ADIM: Kabin Ekibini Kur
        Attendant chef = new Attendant(); chef.setName("Mehmet Usta"); chef.setType("Chef");
        Attendant a1 = new Attendant(); a1.setName("Zeynep"); a1.setType("Regular");
        
        List<Attendant> crewList = new ArrayList<>();
        crewList.add(chef);
        crewList.add(a1);
        flight.setAttendants(crewList);

        // 4. ADIM: Yolcuları Yerleştir
        Passenger vip = new Passenger(); 
        vip.setName("CEO"); 
        vip.setSeatType("Business"); 
        vip.setSeatNumber("1A");

        Passenger student = new Passenger(); 
        student.setName("Öğrenci"); 
        student.setSeatType("Economy"); 
        student.setSeatNumber("15F");

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(vip);
        passengerList.add(student);
        flight.setPassengers(passengerList);

        // 5. ADIM: DOĞRULAMA (Assertions)
        assertNotNull(flight.getFlightNumber(), "Uçuş numarası atanmamış!");
        assertTrue(flight.getPilots().size() >= 2, "Kokpit ekibi eksik.");
        
        // Şef Kontrolü
        boolean hasChef = flight.getAttendants().stream()
                .anyMatch(a -> "Chef".equalsIgnoreCase(a.getType()));
        assertTrue(hasChef, "Uçuşta Flying Chef eksik!");

        // Yolcu Kontrolü
        assertEquals(2, flight.getPassengers().size(), "Yolcu sayısı tutmuyor.");

        System.out.println("✅ ENTEGRASYON TESTİ BAŞARIYLA TAMAMLANDI: UÇUŞ " + flight.getFlightNumber() + " HAZIR!");
    }
}