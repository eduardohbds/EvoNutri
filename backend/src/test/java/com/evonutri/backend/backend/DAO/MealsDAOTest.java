package com.evonutri.backend.backend.DAO;

import br.com.evonutri.EvoNutri.DAO.MealsDAO;
import br.com.evonutri.EvoNutri.Model.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealsDAOTest {

    private Connection connection;
    private MealsDAO mealsDAO;

    @BeforeEach
    void setUp() {
        connection = Mockito.mock(Connection.class);
        mealsDAO = new MealsDAO(connection);
    }

    @Test
    void testAddMeal() throws Exception {
        Meals meal = new Meals("08:00", 500, 30, 20, 10);
        PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        mealsDAO.addMeal(meal);

        verify(stmt, times(1)).setString(1, "08:00");
        verify(stmt, times(1)).setInt(2, 500);
        verify(stmt, times(1)).setInt(3, 30);
        verify(stmt, times(1)).setInt(4, 20);
        verify(stmt, times(1)).setInt(5, 10);
        verify(stmt, times(1)).executeUpdate();
    }

    @Test
    void testGetMeals() throws Exception {
        Statement stmt = Mockito.mock(Statement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        when(connection.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("horario")).thenReturn("08:00");
        when(rs.getInt("calorias")).thenReturn(500);
        when(rs.getInt("proteinas")).thenReturn(30);
        when(rs.getInt("gorduras")).thenReturn(20);
        when(rs.getInt("minerais")).thenReturn(10);

        List<Meals> mealsList = mealsDAO.getMeals();

        assertEquals(1, mealsList.size());
        Meals meal = mealsList.get(0);
        assertEquals("08:00", meal.getHorario());
        assertEquals(500, meal.getCalorias());
        assertEquals(30, meal.getProteinas());
        assertEquals(20, meal.getGorduras());
        assertEquals(10, meal.getMinerais());
    }

    @Test
    void testGetMealById() throws Exception {
        PreparedStatement stmt = Mockito.mock(PreparedStatement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true);
        when(rs.getString("horario")).thenReturn("08:00");
        when(rs.getInt("calorias")).thenReturn(500);
        when(rs.getInt("proteinas")).thenReturn(30);
        when(rs.getInt("gorduras")).thenReturn(20);
        when(rs.getInt("minerais")).thenReturn(10);

        Meals meal = mealsDAO.getMealById(1);

        assertNotNull(meal);
        assertEquals("08:00", meal.getHorario());
        assertEquals(500, meal.getCalorias());
        assertEquals(30, meal.getProteinas());
        assertEquals(20, meal.getGorduras());
        assertEquals(10, meal.getMinerais());
    }

    @Test
    void testUpdateMeal() throws Exception {
        Meals meal = new Meals("12:00", 600, 35, 25, 15);
        PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        mealsDAO.updateMeal(meal, 1);

        verify(stmt, times(1)).setString(1, "12:00");
        verify(stmt, times(1)).setInt(2, 600);
        verify(stmt, times(1)).setInt(3, 35);
        verify(stmt, times(1)).setInt(4, 25);
        verify(stmt, times(1)).setInt(5, 15);
        verify(stmt, times(1)).setInt(6, 1);
        verify(stmt, times(1)).executeUpdate();
    }

    @Test
    void testDeleteMeal() throws Exception {
        PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        mealsDAO.deleteMeal(1);

        verify(stmt, times(1)).setInt(1, 1);
        verify(stmt, times(1)).executeUpdate();
    }
}
