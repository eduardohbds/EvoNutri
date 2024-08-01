package com.evonutri.backend.backend.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;


@Setter
@Getter
@Entity
public class ClienteClass {
    String email;
    String cpf;
    String contato;
}
