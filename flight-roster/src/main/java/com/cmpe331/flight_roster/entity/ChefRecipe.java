package com.cmpe331.flight_roster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chef_recipes")
public class ChefRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recipe_name")
    private String recipeName;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    @JsonIgnore // <-- Sonsuz döngü koruması
    private Attendant attendant;
}