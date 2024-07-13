package com.example.shawarma.dto;

import lombok.Data;
import java.util.List;

@Data
public
class ShawarmaDto {
    private String name;
    private List<Long> ingredientIds;
}