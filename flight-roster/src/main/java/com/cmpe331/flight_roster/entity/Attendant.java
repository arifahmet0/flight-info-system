package com.cmpe331.flight_roster.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "attendants")
public class Attendant {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendant_id")
    private Integer attendantId;

    private String name;
    private Integer age;
    private String gender;
    private String nationality;

    @Column(name = "type") // Chief, Regular, Chef
    private String type;

    // Kabin Memurunun Bildiği Diller (Liste)
    @ElementCollection
    @CollectionTable(name = "attendant_languages", joinColumns = @JoinColumn(name = "attendant_id"))
    @Column(name = "language")
    private List<String> languages;

    // Kabin Memurunun Çalışabildiği Uçak Tipleri (Çoktan Çoğa İlişki)
    @ManyToMany
    @JoinTable(
            name = "attendant_vehicle_restrictions", // Ara tablonun adı
            joinColumns = @JoinColumn(name = "attendant_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_type_id")
    )
    private List<VehicleType> allowedVehicleTypes;

    @ManyToOne
    @JoinColumn(name = "flight_number") // Veritabanındaki Foreign Key sütununun adı
    @com.fasterxml.jackson.annotation.JsonIgnore // Sonsuz döngüye girmesin diye bunu koyuyoruz
    private Flight flight;
    @OneToMany(mappedBy = "attendant", fetch = FetchType.EAGER)
    private List<ChefRecipe> recipes;
}