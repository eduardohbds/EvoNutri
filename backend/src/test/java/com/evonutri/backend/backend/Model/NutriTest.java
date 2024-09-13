package com.evonutri.backend.backend.Model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.Model.Nutri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NutriTest {

    private Nutri nutri;
    private List<Cliente> mockClientes;
    private List<String> mockHorarios;

    @BeforeEach
    public void setUp() {
        // Creating mock data
        Cliente cliente1 = Mockito.mock(Cliente.class);
        Cliente cliente2 = Mockito.mock(Cliente.class);
        mockClientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));
        
        mockHorarios = new ArrayList<>(Arrays.asList("08:00", "14:00", "18:00"));

        // Initializing Nutri object using Builder
        nutri = Nutri.builder()
                .id("12345678900")
                .name("Dr. Nutri")
                .contactNumber("123456789")
                .email("nutri@example.com")
                .cpf("123.456.789-00")
                .crnOrCrm("CRN12345")
                .clientes(mockClientes)
                .horariosDisponiveis(mockHorarios)
                .build();
    }

    @Test
    public void testGetId() {
        assertEquals("12345678900", nutri.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Dr. Nutri", nutri.getName());
    }

    @Test
    public void testGetContactNumber() {
        assertEquals("123456789", nutri.getContactNumber());
    }

    @Test
    public void testGetEmail() {
        assertEquals("nutri@example.com", nutri.getEmail());
    }

    @Test
    public void testGetCpf() {
        assertEquals("123.456.789-00", nutri.getCpf());
    }

    @Test
    public void testGetCrnOrCrm() {
        assertEquals("CRN12345", nutri.getCrnOrCrm());
    }

    @Test
    public void testGetClientes() {
        assertEquals(mockClientes, nutri.getClientes());
        assertEquals(2, nutri.getClientes().size());
    }

    @Test
    public void testSetClientes() {
        Cliente newCliente = Mockito.mock(Cliente.class);
        List<Cliente> newClientes = new ArrayList<>(Arrays.asList(newCliente));
        nutri.setClientes(newClientes);
        
        assertEquals(1, nutri.getClientes().size());
        assertEquals(newCliente, nutri.getClientes().get(0));
    }

    @Test
    public void testGetHorariosDisponiveis() {
        assertEquals(mockHorarios, nutri.getHorariosDisponiveis());
        assertEquals(3, nutri.getHorariosDisponiveis().size());
    }

    @Test
    public void testSetHorariosDisponiveis() {
        List<String> newHorarios = new ArrayList<>(Arrays.asList("09:00", "15:00"));
        nutri.setHorariosDisponiveis(newHorarios);
        
        assertEquals(2, nutri.getHorariosDisponiveis().size());
        assertEquals("09:00", nutri.getHorariosDisponiveis().get(0));
    }

    @Test
    public void testCrnOrCrm() {
        /*
         * Mudar o padrão regex do crn para guardar corretamente
         * 
         * (CRN-\d{2}/\d{4}-\d|\d{4,6}/[A-Z]{2})
        */
        
    }
}
