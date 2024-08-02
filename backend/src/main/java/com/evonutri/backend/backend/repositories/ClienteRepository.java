package com.evonutri.backend.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evonutri.backend.backend.models.ClienteClass;

public interface ClienteRepository extends JpaRepository<ClienteClass, Long> {
    
}