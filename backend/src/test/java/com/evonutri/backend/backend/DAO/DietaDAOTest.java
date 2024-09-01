package com.evonutri.backend.backend.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.evonutri.EvoNutri.DAO.DietaDAO;
import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.Model.Dieta;
import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.Model.Nutri;

public class DietaDAOTest {

    @Mock
    private Connection mockConnection;

    @InjectMocks
    private DietaDAO dietaDAO;

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        dietaDAO = new DietaDAO(mockConnection);
    }

    @Test
    void testAddDieta() throws Exception {
        Nutri nutri = new Nutri("NutriName", "contact", "email", "cpf", "crnOrCrm");
        Cliente cliente = new Cliente("ClienteName", "address", "phone", "age", 70.0, "email", "clienteCpf");
        List<Meals> mealsList = new ArrayList<>();
        Dieta dieta = new Dieta("Descricao", nutri, cliente, "2024-01-01", "2024-12-31", 5, mealsList);

        PreparedStatement mockStmtDieta = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmtDieta);

        dietaDAO.addDieta(dieta);

        verify(mockStmtDieta).setString(1, dieta.getId());
        verify(mockStmtDieta).setString(2, dieta.getDescricao());
        verify(mockStmtDieta).setString(3, dieta.getNutri().getId());
        verify(mockStmtDieta).setString(4, dieta.getCliente().getCpf());
        verify(mockStmtDieta).setString(5, dieta.getDataInicio());
        verify(mockStmtDieta).setString(6, dieta.getDataFim());
        verify(mockStmtDieta).setInt(7, dieta.getQtdRef());
        verify(mockStmtDieta).executeUpdate();
    }

    @Test
    public void testGetDietas() throws SQLException {
        // Setup mock behavior for Statement and ResultSet
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(any(String.class))).thenReturn(rs);

        // Setup ResultSet behavior
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("descricao")).thenReturn("Descricao");
        when(rs.getString("nutri_id")).thenReturn("1");
        when(rs.getString("cliente_id")).thenReturn("clienteCpf");
        when(rs.getString("data_inicio")).thenReturn("2024-01-01");
        when(rs.getString("data_fim")).thenReturn("2024-12-31");
        when(rs.getInt("qtd_ref")).thenReturn(5);
        when(rs.getString("id")).thenReturn("1");

        List<Dieta> dietas = dietaDAO.getDietas();
        assertNotNull(dietas);
        assertEquals(1, dietas.size());
        assertEquals("Descricao", dietas.get(0).getDescricao());
    }

    @Test
    public void testGetDietaById() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("descricao")).thenReturn("Descricao");
        when(rs.getString("nutri_id")).thenReturn("1");
        when(rs.getString("cliente_id")).thenReturn("clienteCpf");
        when(rs.getString("data_inicio")).thenReturn("2024-01-01");
        when(rs.getString("data_fim")).thenReturn("2024-12-31");
        when(rs.getInt("qtd_ref")).thenReturn(5);
        when(rs.getString("id")).thenReturn("1");

        Dieta dieta = dietaDAO.getDietaById("1");
        assertNotNull(dieta);
        assertEquals("Descricao", dieta.getDescricao());
    }

    @Test
    public void testDeleteDieta() throws SQLException {
        PreparedStatement stmtDeleteMeals = mock(PreparedStatement.class);
        PreparedStatement stmtDeleteDieta = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(stmtDeleteMeals).thenReturn(stmtDeleteDieta);

        dietaDAO.deleteDieta("1");

        verify(stmtDeleteMeals).executeUpdate();
        verify(stmtDeleteDieta).executeUpdate();
    }
}
