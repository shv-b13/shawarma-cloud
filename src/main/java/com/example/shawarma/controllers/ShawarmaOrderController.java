package com.example.shawarma.controllers;

import com.example.shawarma.dto.ShawarmaOrderDto;
import com.example.shawarma.models.Shawarma;
import com.example.shawarma.models.ShawarmaOrder;
import com.example.shawarma.repos.ShawarmaOrderRepository;
import com.example.shawarma.repos.ShawarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class ShawarmaOrderController {

    private final ShawarmaOrderRepository shawarmaOrderRepository;
    private final ShawarmaRepository shawarmaRepository;

    @Autowired
    public ShawarmaOrderController(ShawarmaOrderRepository shawarmaOrderRepository, ShawarmaRepository shawarmaRepository) {
        this.shawarmaOrderRepository = shawarmaOrderRepository;
        this.shawarmaRepository = shawarmaRepository;
    }

    @PostMapping
    public ShawarmaOrder createOrder(@RequestBody ShawarmaOrderDto orderDto) {
        ShawarmaOrder order = new ShawarmaOrder();
        order.setDeliveryName(orderDto.getDeliveryName());
        order.setDeliveryStreet(orderDto.getDeliveryStreet());
        order.setDeliveryCity(orderDto.getDeliveryCity());
        order.setDeliveryState(orderDto.getDeliveryState());
        order.setDeliveryZip(orderDto.getDeliveryZip());
        order.setCcNumber(orderDto.getCcNumber());
        order.setCcExpiration(orderDto.getCcExpiration());
        order.setCcCvv(orderDto.getCcCvv());
        order.setPlacedAt(new Timestamp(System.currentTimeMillis()));

        List<Shawarma> shawarmas = shawarmaRepository.findAllById(orderDto.getShawarmaIds());
        order.setShawarmas(shawarmas);

        return shawarmaOrderRepository.save(order);
    }
}