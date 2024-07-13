package com.example.shawarma.models;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
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


}
