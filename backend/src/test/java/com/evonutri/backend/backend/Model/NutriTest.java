package com.evonutri.backend.backend.Model;
import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.Model.Nutri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NutriTest {

    private Nutri nutri;

    @BeforeEach
    void setUp() {
        nutri = new Nutri("John Doe", "123456789", "johndoe@example.com", "12345678901", "CRM12345");
    }

    @Test
    void testConstructor() {
        assertNotNull(nutri.getId()); // ID should be generated
        assertEquals("John Doe", nutri.getName());
        assertEquals("123456789", nutri.getContactNumber());
        assertEquals("johndoe@example.com", nutri.getEmail());
        assertEquals("12345678901", nutri.getCpf());
        assertEquals("CRM12345", nutri.getCrnOrCrm());
        assertTrue(nutri.getClientes().isEmpty()); // No clients should be present initially
        assertTrue(nutri.getHorariosDisponiveis().isEmpty()); // No available hours should be present initially
    }

    @Test
    void testSetters() {
        nutri.setName("Jane Doe");
        nutri.setContactNumber("987654321");
        nutri.setEmail("janedoe@example.com");
        nutri.setCpf("09876543210");
        nutri.setCrnOrCrm("CRM54321");

        assertEquals("Jane Doe", nutri.getName());
        assertEquals("987654321", nutri.getContactNumber());
        assertEquals("janedoe@example.com", nutri.getEmail());
        assertEquals("09876543210", nutri.getCpf());
        assertEquals("CRM54321", nutri.getCrnOrCrm());
    }

    @Test
    void testAddCliente() {
        Cliente cliente = new Cliente("Client Name", "Client Address", "Client Phone", "30", 70.0, "client@example.com", "12345678901");
        nutri.addCliente(cliente);

        assertFalse(nutri.getClientes().isEmpty());
        assertEquals(1, nutri.getClientes().size());
        assertEquals(cliente, nutri.getClientes().get(0));
    }

    @Test
    void testAddHorarioDisponivel() {
        nutri.addHorarioDisponivel("08:00 - 09:00");
        nutri.addHorarioDisponivel("10:00 - 11:00");

        List<String> expectedHorarios = new ArrayList<>();
        expectedHorarios.add("08:00 - 09:00");
        expectedHorarios.add("10:00 - 11:00");

        assertEquals(expectedHorarios, nutri.getHorariosDisponiveis());
    }

    @Test
    void testToString() {
        String expected = "Nutricionista [id=" + nutri.getId() + ", name=John Doe, contactNumber=123456789, email=johndoe@example.com, cpf=12345678901, crnOrCrm=CRM12345, clientes=[], horariosDisponiveis=[]]";
        assertEquals(expected, nutri.toString());
    }

    @Test
    void testSetClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente("Client Name", "Client Address", "Client Phone", "30", 70.0, "client@example.com", "12345678901");
        clientes.add(cliente);

        // We expect this to throw an UnsupportedOperationException as per the implementation of setClientes
        assertThrows(UnsupportedOperationException.class, () -> nutri.setClientes(clientes));
    }
}
