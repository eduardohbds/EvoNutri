package com.evonutri.backend.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evonutri.backend.backend.validator.ValidadorCliente;

@Component
public class ClienteController {
    private final ValidadorCliente validadorCliente;

    @Autowired
    public ClienteController(ValidadorCliente validadorCliente) {
        this.validadorCliente = validadorCliente;
    }

    public void verificarEmail() {
        // Implementation
    }

    public void verificarPassword() {
        // Implementation
    }

    public void cadastrarConsulta() {
        // Implementation
    }

    public void cadastrarRelatorio() {
        // Implementation
    }

    public void encaminharAnamnese() {
        // Implementation
    }

    public void cadastrarDieta() {
        // Implementation
    }

    public void buscarRelatorio() {
        // Implementation
    }
}
