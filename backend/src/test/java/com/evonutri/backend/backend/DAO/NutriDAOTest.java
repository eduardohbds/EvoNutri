package com.evonutri.backend.backend.DAO;

import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.Model.Nutri;
import br.com.evonutri.EvoNutri.DAO.NutriDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class NutriDAOTest {

    @Mock
    private Connection connection;
    
    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private NutriDAO nutriDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        nutriDAO = new NutriDAO();
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testAddNutricionista() throws SQLException {
        Nutri nutri = new Nutri("John Doe", "1234567890", "johndoe@example.com", "12345678901", "12345");
        nutriDAO.addNutricionista(nutri);
        verify(preparedStatement, times(1)).execute();
    }

    @Test
    void testGetAllNutricionistas() throws SQLException {
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("id")).thenReturn("1");
        when(resultSet.getString("name")).thenReturn("John Doe");
        when(resultSet.getString("contactNumber")).thenReturn("1234567890");
        when(resultSet.getString("email")).thenReturn("johndoe@example.com");
        when(resultSet.getString("cpf")).thenReturn("12345678901");
        when(resultSet.getString("crnOrCrm")).thenReturn("12345");

        List<Nutri> nutricionistas = nutriDAO.getAllNutricionistas();
        assertEquals(1, nutricionistas.size());
        assertEquals("John Doe", nutricionistas.get(0).getName());
    }

    @Test
    void testGetNutricionistaById() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("id")).thenReturn("1");
        when(resultSet.getString("name")).thenReturn("John Doe");
        when(resultSet.getString("contactNumber")).thenReturn("1234567890");
        when(resultSet.getString("email")).thenReturn("johndoe@example.com");
        when(resultSet.getString("cpf")).thenReturn("12345678901");
        when(resultSet.getString("crnOrCrm")).thenReturn("12345");

        Nutri nutricionista = nutriDAO.getNutricionistaById("1");
        assertNotNull(nutricionista);
        assertEquals("John Doe", nutricionista.getName());
    }

    @Test
    void testUpdateNutricionista() throws SQLException {
        Nutri nutri = new Nutri("John Doe", "1234567890", "johndoe@example.com", "12345678901", "12345");
        nutriDAO.updateNutricionista(nutri);
        verify(preparedStatement, times(1)).execute();
    }

    @Test
    void testDeleteNutricionista() throws SQLException {
        nutriDAO.deleteNutricionista("1");
        verify(preparedStatement, times(3)).execute(); // One for each delete operation
    }
}
