package com.evonutri.backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evonutri.backend.backend.models.NutricionistaClass;

public interface NutriRepository extends JpaRepository<NutricionistaClass, Long> {
    // ... custom queries
}

