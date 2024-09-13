package com.evonutri.backend.backend.Model;
import br.com.evonutri.EvoNutri.Model.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealsTest {

    private Meals meal;

    @BeforeEach
    public void setUp() {
        meal = new Meals("08:00", 500, 30, 20, 10);
    }

    @Test
    public void testGetHorario() {
        assertEquals("08:00", meal.getHorario());
    }

    @Test
    public void testSetHorario() {
        meal.setHorario("12:00");
        assertEquals("12:00", meal.getHorario());
    }

    @Test
    public void testGetCalorias() {
        assertEquals(500, meal.getCalorias());
    }

    @Test
    public void testSetCalorias() {
        meal.setCalorias(600);
        assertEquals(600, meal.getCalorias());
    }

    @Test
    public void testGetProteinas() {
        assertEquals(30, meal.getProteinas());
    }

    @Test
    public void testSetProteinas() {
        meal.setProteinas(40);
        assertEquals(40, meal.getProteinas());
    }

    @Test
    public void testGetGorduras() {
        assertEquals(20, meal.getGorduras());
    }

    @Test
    public void testSetGorduras() {
        meal.setGorduras(25);
        assertEquals(25, meal.getGorduras());
    }

    @Test
    public void testGetMinerais() {
        assertEquals(10, meal.getMinerais());
    }

    @Test
    public void testSetMinerais() {
        meal.setMinerais(15);
        assertEquals(15, meal.getMinerais());
    }

    @Test
    public void testToString() {
        String expected = "Meals [horario=08:00, calorias=500, proteinas=30, gorduras=20, minerais=10]";
        assertEquals(expected, meal.toString());
    }
}
