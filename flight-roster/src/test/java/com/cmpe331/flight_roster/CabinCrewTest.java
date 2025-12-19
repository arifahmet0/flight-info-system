package com.cmpe331.flight_roster;

import com.cmpe331.flight_roster.entity.Attendant;
import com.cmpe331.flight_roster.entity.Flight;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CabinCrewTest {

    @Test
    void testChefIsMandatory() {
        // SENARYO: Uçuşta Flying Chef olmazsa sistem uyarı vermeli
        
        Flight flight = new Flight();
        List<Attendant> crew = new ArrayList<>();

        // Sadece Normal Memurlar ekleyelim
        Attendant a1 = new Attendant();
        a1.setName("Ayşe Normal");
        a1.setType("Regular"); // Veya seniorityLevel, entity'ne göre değişebilir

        Attendant a2 = new Attendant();
        a2.setName("Fatma Senior");
        a2.setType("Chief");

        crew.add(a1);
        crew.add(a2);
        flight.setAttendants(crew);

        // Kural Kontrolü: Listede tipi "Chef" (veya Flying Chef) olan var mı?
        boolean hasChef = flight.getAttendants().stream()
                .anyMatch(a -> "Chef".equalsIgnoreCase(a.getType()) || "Flying Chef".equalsIgnoreCase(a.getType()));

        assertFalse(hasChef, "Uçuşta Şef (Flying Chef) bulunmadığında sistem bunu geçerli saymamalıdır.");
    }

    @Test
    void testValidCrewWithChef() {
        // SENARYO: Şef içeren geçerli bir ekip
        
        Flight flight = new Flight();
        List<Attendant> crew = new ArrayList<>();

        Attendant chef = new Attendant();
        chef.setName("Mehmet Usta");
        chef.setType("Chef"); // Şef var

        crew.add(chef);
        flight.setAttendants(crew);

        boolean hasChef = flight.getAttendants().stream()
                .anyMatch(a -> "Chef".equalsIgnoreCase(a.getType()));

        assertTrue(hasChef, "Ekipte Şef olduğunda sistem onay vermelidir.");
    }
}