package com.cmpe331.flight_roster.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Getter, Setter, toString metodlarını otomatik oluşturur
@Entity // Bu sınıfın bir veritabanı tablosu olduğunu belirtir
@Table(name = "airports") // MySQL'deki 'airports' tablosuna bağlanır
public class Airport {

    @Id
    @Column(length = 3)
    private String code; // IST, ESB vb.

    private String name;
    private String city;
    private String country;
}