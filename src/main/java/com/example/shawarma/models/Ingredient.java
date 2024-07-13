package com.example.shawarma.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Type type;

    public Ingredient(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    @ManyToMany(mappedBy = "ingredients")
    private List<Shawarma> shawarmas;
}
