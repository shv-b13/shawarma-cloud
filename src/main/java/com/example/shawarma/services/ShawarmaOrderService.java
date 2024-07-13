package com.example.shawarma.services;

import com.example.shawarma.models.ShawarmaOrder;
import com.example.shawarma.repos.ShawarmaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShawarmaOrderService {
    private final ShawarmaOrderRepository shawarmaOrderRepository;

    @Autowired
    private ShawarmaOrderService(ShawarmaOrderRepository shawarmaOrderRepository){
        this.shawarmaOrderRepository = shawarmaOrderRepository;
    }

    public List<ShawarmaOrder> findAll(){
        return shawarmaOrderRepository.findAll();
    }

    public ShawarmaOrder save(ShawarmaOrder shawarmaOrder){
        return shawarmaOrderRepository.save(shawarmaOrder);
    }
}
