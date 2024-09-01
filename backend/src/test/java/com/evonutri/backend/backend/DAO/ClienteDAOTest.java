package com.evonutri.backend.backend.DAO;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.DAO.ClienteDAO;
import br.com.evonutri.EvoNutri.DAO.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ClienteDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ClienteDAO clienteDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(ConnectionManager.getConnection()).thenReturn(connection);
    }

    @Test
    void testAddCliente() throws SQLException {
        Cliente cliente = new Cliente("John", "123 St", "555-1234", "30", 75.0, "john@example.com", "12345678900");

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        
        clienteDAO.addCliente(cliente);

        verify(preparedStatement, times(1)).setString(1, cliente.getName());
        verify(preparedStatement, times(1)).setString(2, cliente.getEndereco());
        verify(preparedStatement, times(1)).setString(3, cliente.getPhone());
        verify(preparedStatement, times(1)).setString(4, cliente.getAge());
        verify(preparedStatement, times(1)).setDouble(5, cliente.getWeight());
        verify(preparedStatement, times(1)).setString(6, cliente.getEmail());
        verify(preparedStatement, times(1)).setString(7, cliente.getCpf());
        verify(preparedStatement, times(1)).execute();
    }

    @Test
    void testGetAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente("John", "123 St", "555-1234", "30", 75.0, "john@example.com", "12345678900");
        clientes.add(cliente);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(cliente.getName());
        when(resultSet.getString("endereco")).thenReturn(cliente.getEndereco());
        when(resultSet.getString("phone")).thenReturn(cliente.getPhone());
        when(resultSet.getString("age")).thenReturn(cliente.getAge());
        when(resultSet.getDouble("weight")).thenReturn(cliente.getWeight());
        when(resultSet.getString("email")).thenReturn(cliente.getEmail());
        when(resultSet.getString("cpf")).thenReturn(cliente.getCpf());

        List<Cliente> result = clienteDAO.getAllClientes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cliente, result.get(0));
    }

    @Test
    void testGetClienteByCpf() throws SQLException {
        String cpf = "12345678900";
        Cliente cliente = new Cliente("John", "123 St", "555-1234", "30", 75.0, "john@example.com", cpf);

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(cliente.getName());
        when(resultSet.getString("endereco")).thenReturn(cliente.getEndereco());
        when(resultSet.getString("phone")).thenReturn(cliente.getPhone());
        when(resultSet.getString("age")).thenReturn(cliente.getAge());
        when(resultSet.getDouble("weight")).thenReturn(cliente.getWeight());
        when(resultSet.getString("email")).thenReturn(cliente.getEmail());
        when(resultSet.getString("cpf")).thenReturn(cliente.getCpf());

        Cliente result = clienteDAO.getClienteByCpf(cpf);

        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void testUpdateCliente() throws SQLException {
        Cliente cliente = new Cliente("John", "123 St", "555-1234", "30", 75.0, "john@example.com", "12345678900");

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        clienteDAO.updateCliente(cliente);

        verify(preparedStatement, times(1)).setString(1, cliente.getName());
        verify(preparedStatement, times(1)).setString(2, cliente.getEndereco());
        verify(preparedStatement, times(1)).setString(3, cliente.getPhone());
        verify(preparedStatement, times(1)).setString(4, cliente.getAge());
        verify(preparedStatement, times(1)).setDouble(5, cliente.getWeight());
        verify(preparedStatement, times(1)).setString(6, cliente.getEmail());
        verify(preparedStatement, times(1)).setString(7, cliente.getCpf());
        verify(preparedStatement, times(1)).execute();
    }

    @Test
    void testDeleteCliente() throws SQLException {
        String cpf = "12345678900";

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        clienteDAO.deleteCliente(cpf);

        verify(preparedStatement, times(1)).setString(1, cpf);
        verify(preparedStatement, times(1)).execute();
    }
}

