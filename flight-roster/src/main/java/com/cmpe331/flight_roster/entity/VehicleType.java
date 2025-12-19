package com.cmpe331.flight_roster.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_types")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "seat_count", nullable = false)
    private Integer seatCount;

    @Column(name = "seating_plan_json", columnDefinition = "TEXT")
    private String seatingPlanJson; // JSON verisini String olarak tutuyoruz

    @Column(name = "standard_menu", columnDefinition = "TEXT")
    private String standardMenu;
}