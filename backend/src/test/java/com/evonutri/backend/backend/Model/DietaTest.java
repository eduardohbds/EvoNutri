package com.evonutri.backend.backend.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.evonutri.EvoNutri.Model.*;

public class DietaTest {

    private Nutri mockNutri;
    private Cliente mockCliente;
    private Meals mockMeal1;
    private Meals mockMeal2;

    @BeforeEach
    public void setUp() {
        // Mock the Nutri and Cliente objects
        mockNutri = mock(Nutri.class);
        mockCliente = mock(Cliente.class);

        // Mock the Meals objects
        mockMeal1 = mock(Meals.class);
        mockMeal2 = mock(Meals.class);
    }

    @Test
    public void testDietaBuilder() {
        // Arrange
        List<Meals> refeicoes = Arrays.asList(mockMeal1, mockMeal2);

        // Act
        Dieta dieta = Dieta.builder()
                .id("123")
                .descricao("Dieta para perda de peso")
                .nutri(mockNutri)
                .cliente(mockCliente)
                .dataInicio("2024-09-01")
                .dataFim("2024-12-01")
                .qtdRef(5)
                .refeicoes(refeicoes)
                .build();

        // Assert
        assertNotNull(dieta);
        assertEquals("123", dieta.getId());
        assertEquals("Dieta para perda de peso", dieta.getDescricao());
        assertEquals(mockNutri, dieta.getNutri());
        assertEquals(mockCliente, dieta.getCliente());
        assertEquals("2024-09-01", dieta.getDataInicio());
        assertEquals("2024-12-01", dieta.getDataFim());
        assertEquals(5, dieta.getQtdRef());
        assertEquals(refeicoes, dieta.getRefeicoes());
    }

    @Test
    public void testSetAndGetFields() {
        // Arrange
        Dieta dieta = Dieta.builder().build(); // Create an empty Dieta object

        List<Meals> refeicoes = Arrays.asList(mockMeal1, mockMeal2);

        // Act
        dieta.setId("123");
        dieta.setDescricao("Dieta para ganho de massa");
        dieta.setNutri(mockNutri);
        dieta.setCliente(mockCliente);
        dieta.setDataInicio("2024-01-01");
        dieta.setDataFim("2024-03-01");
        dieta.setQtdRef(4);
        dieta.setRefeicoes(refeicoes);

        // Assert
        assertEquals("123", dieta.getId());
        assertEquals("Dieta para ganho de massa", dieta.getDescricao());
        assertEquals(mockNutri, dieta.getNutri());
        assertEquals(mockCliente, dieta.getCliente());
        assertEquals("2024-01-01", dieta.getDataInicio());
        assertEquals("2024-03-01", dieta.getDataFim());
        assertEquals(4, dieta.getQtdRef());
        assertEquals(refeicoes, dieta.getRefeicoes());
    }
}
