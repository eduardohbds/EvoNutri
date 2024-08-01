package com.evonutri.backend.backend.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;

@Setter
@Getter
@Entity
public class NutricionistaClass {
    String crnm;
    boolean tipo;
    String zona;
}

