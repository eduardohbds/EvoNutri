package br.com.evonutri.EvoNutri.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.evonutri.EvoNutri.Model.Cliente;

public class ClienteDAO {

    public void addCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (name, endereco, phone, age, weight, email, cpf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getPhone());
            stmt.setString(4, cliente.getAge());
            stmt.setDouble(5, cliente.getWeight());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getCpf());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar cliente", e);
        }
    }

    public List<Cliente> getAllClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("name"),
                        rs.getString("endereco"),
                        rs.getString("phone"),
                        rs.getString("age"),
                        rs.getDouble("weight"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar todos os clientes", e);
        }
        return clientes;
    }

    public Cliente getClienteByCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getString("name"),
                            rs.getString("endereco"),
                            rs.getString("phone"),
                            rs.getString("age"),
                            rs.getDouble("weight"),
                            rs.getString("email"),
                            rs.getString("cpf")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar cliente pelo CPF", e);
        }
        return null;
    }

    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET name = ?, endereco = ?, phone = ?, age = ?, weight = ?, email = ? WHERE cpf = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getName());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getPhone());
            stmt.setString(4, cliente.getAge());
            stmt.setDouble(5, cliente.getWeight());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getCpf());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public void deleteCliente(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }
}
