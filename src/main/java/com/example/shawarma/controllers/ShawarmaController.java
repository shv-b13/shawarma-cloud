package com.example.shawarma.controllers;

import com.example.shawarma.dto.ShawarmaDto;
import com.example.shawarma.models.Ingredient;
import com.example.shawarma.models.Shawarma;
import com.example.shawarma.repos.IngredientRepository;
import com.example.shawarma.repos.ShawarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shawarma")
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
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("shawarma", new Shawarma());
        return "createShawarma";
    }

    @PostMapping("/create")
    public String createShawarma(@ModelAttribute Shawarma shawarma, Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAllById(
                shawarma.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toList())
        );
        shawarma.setIngredients(ingredients);
        shawarma.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shawarmaRepository.save(shawarma);
        return "redirect:/shawarma/list";
    }

    @GetMapping("/list")
    public String listShawarmas(Model model) {
        model.addAttribute("shawarmas", shawarmaRepository.findAll());
        return "shawarmaList";
    }
}

