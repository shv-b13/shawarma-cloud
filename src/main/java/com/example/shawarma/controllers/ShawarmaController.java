package com.example.shawarma.controllers;

import com.example.shawarma.dto.ShawarmaDto;
import com.example.shawarma.models.Ingredient;
import com.example.shawarma.models.Shawarma;
import com.example.shawarma.repos.IngredientRepository;
import com.example.shawarma.repos.ShawarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/shawarmas")
public class ShawarmaController {

    private final ShawarmaRepository shawarmaRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ShawarmaController(ShawarmaRepository shawarmaRepository, IngredientRepository ingredientRepository) {
        this.shawarmaRepository = shawarmaRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/create")
    public String showCreateShawarmaForm(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("shawarma", new Shawarma());
        return "createShawarma";
    }

    @PostMapping("/create")
    public Shawarma createShawarma(@RequestBody ShawarmaDto shawarmaDto) {
        Shawarma shawarma = new Shawarma();
        shawarma.setName(shawarmaDto.getName());
        shawarma.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        List<Ingredient> ingredients = ingredientRepository.findAllById(shawarmaDto.getIngredientIds());
        shawarma.setIngredients(ingredients);

        return shawarmaRepository.save(shawarma);
    }
}