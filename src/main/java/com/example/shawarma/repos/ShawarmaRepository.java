package com.example.shawarma.repos;

import com.example.shawarma.models.Shawarma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShawarmaRepository extends JpaRepository<Shawarma, Long> {

}
