package com.example.shawarma.repos;

import com.example.shawarma.models.ShawarmaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShawarmaOrderRepository extends JpaRepository<ShawarmaOrder, Long> {

}
