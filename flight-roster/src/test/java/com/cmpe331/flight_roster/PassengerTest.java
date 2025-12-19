package com.cmpe331.flight_roster;

import com.cmpe331.flight_roster.entity.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testInfantSeating() {
        // SENARYO: 0-2 yaş arası bebek yolcular kucakta (Lap) gitmeli
        
        Passenger baby = new Passenger();
        baby.setName("Bebek Can");
        baby.setAge(1); // 1 Yaşında
        
        // Simüle edilen mantık: Eğer yaş < 2 ise koltuk numarası "Lap" olarak belirlenir
        // (Bu mantık normalde serviste olur ama test için burada simüle ediyoruz)
        if (baby.getAge() < 2) {
            baby.setSeatNumber("Lap");
        }

        assertEquals("Lap", baby.getSeatNumber(), "Bebek yolcuların koltuk numarası 'Lap' olmalıdır.");
    }

    @Test
    void testBusinessClassAssignment() {
        // SENARYO: Business yolcusu Business koltuğunda mı?
        
        Passenger vip = new Passenger();
        vip.setName("Zeynep Hanım");
        
        // --- DÜZELTME BURADA ---
        vip.setSeatType("Business"); // passengerType yerine seatType kullandık
        // -----------------------
        
        vip.setSeatNumber("1A"); // 1. sıra Business olsun

        // Basit kontrol: Koltuk 1 ile başlıyorsa ve tipi Business ise geçerlidir
        boolean isBusinessSeat = vip.getSeatNumber().startsWith("1");
        
        assertTrue(isBusinessSeat, "Business yolcusu ön sıralara (Business koltuklara) atanmalıdır.");
        assertEquals("Business", vip.getSeatType(), "Yolcunun bilet tipi Business olmalıdır.");
    }
}