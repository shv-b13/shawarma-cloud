package com.example.shawarma.controllers;

import com.example.shawarma.dto.ShawarmaOrderDto;
import com.example.shawarma.models.Shawarma;
import com.example.shawarma.models.ShawarmaOrder;
import com.example.shawarma.repos.ShawarmaOrderRepository;
import com.example.shawarma.repos.ShawarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class ShawarmaOrderController {

    private final ShawarmaOrderRepository shawarmaOrderRepository;
    private final ShawarmaRepository shawarmaRepository;

    @Autowired
    public ShawarmaOrderController(ShawarmaOrderRepository shawarmaOrderRepository, ShawarmaRepository shawarmaRepository) {
        this.shawarmaOrderRepository = shawarmaOrderRepository;
        this.shawarmaRepository = shawarmaRepository;
    }

    @GetMapping("/create/{shawarmaId}")
    public String showCreateOrderForm(@PathVariable("shawarmaId") Long shawarmaId, Model model) {
        ShawarmaOrder order = new ShawarmaOrder();
        // Находим шаурму по идентификатору
        Shawarma shawarma = shawarmaRepository.findById(shawarmaId).orElseThrow(() -> new IllegalArgumentException("Invalid Shawarma ID"));
        order.getShawarmas().add(shawarma); // Добавляем шаурму в заказ
        model.addAttribute("order", order);
        return "createOrder";
    }
//    @GetMapping("/create")
//    public String showCreateOrderForm(Model model) {
//        model.addAttribute("order", new ShawarmaOrder());
//        return "createOrder";
//    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute ShawarmaOrder order, Model model) {
        if (order.getShawarmas() == null) {
            order.setShawarmas(new ArrayList<>());  // Инициализация списка, если он равен null
        }
        List<Shawarma> shawarmas = shawarmaRepository.findAllById(
                order.getShawarmas().stream().map(Shawarma::getId).collect(Collectors.toList())
        );
        order.setShawarmas(shawarmas);
        order.setPlacedAt(new Timestamp(System.currentTimeMillis()));
        shawarmaOrderRepository.save(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", shawarmaOrderRepository.findAll());
        return "orderList";
    }
}