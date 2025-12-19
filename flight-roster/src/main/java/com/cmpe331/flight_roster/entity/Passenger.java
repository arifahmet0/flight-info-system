package com.cmpe331.flight_roster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // BU IMPORT ÖNEMLİ
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    // --- DÜZELTME BURADA ---
    @ManyToOne
    @JoinColumn(name = "flight_number")
    @JsonIgnore  // <--- BUNU EKLEMEZSEN SONSUZ DÖNGÜYE GİRER!
    private Flight flight;
    // -----------------------

    private String name;
    private Integer age;
    private String gender;
    private String nationality;

    @Column(name = "seat_type")
    private String seatType; // Business, Economy

    @Column(name = "seat_number")
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "parent_passenger_id")
    private Passenger parentPassenger;
}