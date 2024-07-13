package com.example.shawarma.models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShawarmaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCvv;
    private Timestamp placedAt;

    @OneToMany(mappedBy = "shawarmaOrder")
    private List<Shawarma> shawarmas;

    public ShawarmaOrder() {
        this.shawarmas = new ArrayList<>();  // Инициализация в конструкторе по умолчанию
    }

    // Если используется кастомный конструктор, не забудьте инициализировать список в нем
    public ShawarmaOrder(String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState, String deliveryZip, String ccNumber, String ccExpiration, String ccCvv) {
        this.deliveryName = deliveryName;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCvv = ccCvv;
        this.shawarmas = new ArrayList<>();  // Инициализация списка
    }
}
