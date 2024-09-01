package com.evonutri.backend.backend.Model;
import br.com.evonutri.EvoNutri.Model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Jane Doe", "123 Main St", "9876543210", "30", 65.5, "janedoe@example.com", "12345678901");
    }

    @Test
    void testGetters() {
        assertEquals("Jane Doe", cliente.getName());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("9876543210", cliente.getPhone());
        assertEquals("30", cliente.getAge());
        assertEquals(65.5, cliente.getWeight());
        assertEquals("janedoe@example.com", cliente.getEmail());
        assertEquals("12345678901", cliente.getCpf());
    }

    @Test
    void testSetters() {
        cliente.setName("John Doe");
        cliente.setEndereco("456 Elm St");
        cliente.setPhone("1234567890");
        cliente.setAge("40");
        cliente.setWeight(70.0);
        cliente.setEmail("johndoe@example.com");
        cliente.setCpf("09876543210");

        assertEquals("John Doe", cliente.getName());
        assertEquals("456 Elm St", cliente.getEndereco());
        assertEquals("1234567890", cliente.getPhone());
        assertEquals("40", cliente.getAge());
        assertEquals(70.0, cliente.getWeight());
        assertEquals("johndoe@example.com", cliente.getEmail());
        assertEquals("09876543210", cliente.getCpf());
    }

    @Test
    void testToString() {
        String expected = "Cliente [getName()=Jane Doe, getEndereco()=123 Main St, getPhone()=9876543210, getAge()=30, getWeight()=65.5, getEmail()=janedoe@example.com, getCpf()=12345678901, getClass()=class br.com.evonutri.EvoNutri.Model.Cliente]";
        assertEquals(expected, cliente.toString());
    }

    @Test
    void testClienteConstructor() {
        assertNotNull(cliente);
        assertEquals("Jane Doe", cliente.getName());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("9876543210", cliente.getPhone());
        assertEquals("30", cliente.getAge());
        assertEquals(65.5, cliente.getWeight());
        assertEquals("janedoe@example.com", cliente.getEmail());
        assertEquals("12345678901", cliente.getCpf());
    }
}
