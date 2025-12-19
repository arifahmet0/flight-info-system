package com.cmpe331.flight_roster.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @Column(name = "flight_number", length = 6)
    private String flightNumber; // Örn: TK1234


    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "distance_km")
    private Integer distanceKm;

    // Kalkış Havalimanı
    @ManyToOne
    @JoinColumn(name = "source_code")
    private Airport sourceAirport;

    // Varış Havalimanı
    @ManyToOne
    @JoinColumn(name = "destination_code")
    private Airport destinationAirport;

    // Uçak Tipi
    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private java.util.List<Passenger> passengers;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private java.util.List<Pilot> pilots;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private java.util.List<Attendant> attendants;
}