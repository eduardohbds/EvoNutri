package com.evonutri.backend.backend.Model;

import br.com.evonutri.EvoNutri.Model.Meals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealsTest {

    @Test
    void testMealsConstructorAndGetters() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);

        assertEquals("08:00", meal.getHorario());
        assertEquals(500, meal.getCalorias());
        assertEquals(30, meal.getProteinas());
        assertEquals(20, meal.getGorduras());
        assertEquals(10, meal.getMinerais());
    }

    @Test
    void testSetHorario() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        meal.setHorario("12:00");

        assertEquals("12:00", meal.getHorario());
    }

    @Test
    void testSetCalorias() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        meal.setCalorias(600);

        assertEquals(600, meal.getCalorias());
    }

    @Test
    void testSetProteinas() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        meal.setProteinas(40);

        assertEquals(40, meal.getProteinas());
    }

    @Test
    void testSetGorduras() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        meal.setGorduras(25);

        assertEquals(25, meal.getGorduras());
    }

    @Test
    void testSetMinerais() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        meal.setMinerais(15);

        assertEquals(15, meal.getMinerais());
    }

    @Test
    void testToString() {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        String expectedString = "Meals [horario=08:00, calorias=500, proteinas=30, gorduras=20, minerais=10]";

        assertEquals(expectedString, meal.toString());
    }
}
