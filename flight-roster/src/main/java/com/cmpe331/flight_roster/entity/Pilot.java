package com.cmpe331.flight_roster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "pilots")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pilot_id")
    private Integer pilotId;

    private String name;
    private Integer age;
    private String gender;
    private String nationality;

    @Column(name = "seniority_level")
    private String seniorityLevel;

    @Column(name = "allowed_range_km")
    private Integer allowedRangeKm;

    @ManyToOne
    @JoinColumn(name = "allowed_vehicle_type_id")
    private VehicleType allowedVehicleType;

    @ElementCollection
    @CollectionTable(name = "pilot_languages", joinColumns = @JoinColumn(name = "pilot_id"))
    @Column(name = "language")
    private List<String> languages;

    // ... diğer değişkenler ...

    // BU KISMI EKLE 👇
    @ManyToOne
    @JoinColumn(name = "flight_number")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Flight flight;
}