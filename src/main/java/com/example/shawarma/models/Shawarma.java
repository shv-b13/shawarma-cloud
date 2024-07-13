package com.example.shawarma.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shawarma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "shawarma_order_id")
    private ShawarmaOrder shawarmaOrder;

    @ManyToMany
    @JoinTable(
            name = "Shawarma_Ingredient",
            joinColumns = @JoinColumn(name = "shawarma_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
}
