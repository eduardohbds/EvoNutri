package br.com.evonutri.EvoNutri.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.evonutri.EvoNutri.Model.Nutri;
import br.com.evonutri.EvoNutri.Model.Cliente;

public class NutriDAO {

    public void addNutricionista(Nutri nutricionista) {
        String sql = "INSERT INTO nutricionistas (id, name, contactNumber, email, cpf, crnOrCrm) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nutricionista.getId());
            stmt.setString(2, nutricionista.getName());
            stmt.setString(3, nutricionista.getContactNumber());
            stmt.setString(4, nutricionista.getEmail());
            stmt.setString(5, nutricionista.getCpf());
            stmt.setString(6, nutricionista.getCrnOrCrm());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar nutricionista", e);
        }

        // Inserindo clientes e horários
        addClientes(nutricionista);
        addHorarios(nutricionista);
    }

    private void addClientes(Nutri nutricionista) {
        String sql = "INSERT INTO nutricionista_clientes (nutricionista_id, cliente_cpf) VALUES (?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Cliente cliente : nutricionista.getClientes()) {
                stmt.setString(1, nutricionista.getId());
                stmt.setString(2, cliente.getCpf());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar clientes do nutricionista", e);
        }
    }

    private void addHorarios(Nutri nutricionista) {
        String sql = "INSERT INTO nutricionista_horarios (nutricionista_id, horario) VALUES (?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String horario : nutricionista.getHorariosDisponiveis()) {
                stmt.setString(1, nutricionista.getId());
                stmt.setString(2, horario);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar horários do nutricionista", e);
        }
    }

    public List<Nutri> getAllNutricionistas() {
        String sql = "SELECT * FROM nutricionistas";
        List<Nutri> nutricionistas = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Nutri nutricionista = Nutri.builder()
                        .name(rs.getString("name"))
                        .contactNumber(rs.getString("contactNumber"))
                        .email(rs.getString("email"))
                        .cpf(rs.getString("cpf"))
                        .crnOrCrm(rs.getString("crnOrCrm"))
                        .build();

                nutricionista.setId(rs.getString("id")); // Set ID separately if needed
                nutricionista.setClientes(getClientes(nutricionista.getId()));
                nutricionista.setHorariosDisponiveis(getHorarios(nutricionista.getId()));

                nutricionistas.add(nutricionista);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar todos os nutricionistas", e);
        }
        return nutricionistas;
    }

    public Nutri getNutricionistaById(String id) {
        String sql = "SELECT * FROM nutricionistas WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Nutri nutricionista = new Nutri(
                    //         rs.getString("name"),
                    //         rs.getString("contactNumber"),
                    //         rs.getString("email"),
                    //         rs.getString("cpf"),
                    //         rs.getString("crnOrCrm"));
                    Nutri nutricionista = Nutri.builder()
                    .name(rs.getString("name"))
                    .contactNumber(rs.getString("contactNumber"))
                    .email(rs.getString("email"))
                    .cpf(rs.getString("cpf"))
                    .crnOrCrm(rs.getString("crnOrCrm")).build();

                    nutricionista.setCpf(rs.getString("id"));
                    nutricionista.setClientes(getClientes(nutricionista.getId()));
                    nutricionista.setHorariosDisponiveis(getHorarios(nutricionista.getId()));
                    return nutricionista;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar nutricionista pelo ID", e);
        }
        return null;
    }

    private List<Cliente> getClientes(String string) {
        String sql = "SELECT * FROM clientes WHERE cpf IN (SELECT cliente_cpf FROM nutricionista_clientes WHERE nutricionista_id = ?)";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, string);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Cliente cliente = new Cliente(
                    //         rs.getString("name")
                    //         rs.getString("endereco")
                    //         rs.getString("phone")
                    //         rs.getString("age")
                    //         rs.getDouble("weight")
                    //         rs.getString("email")
                    //         rs.getString("cpf"))
                    Cliente cliente = Cliente.builder()
                    .name(rs.getString("name"))
                    .endereco(rs.getString("endereco"))
                    .phone(rs.getString("phone"))
                    .age(rs.getString("age"))
                    .weight(rs.getDouble("weight"))
                    .email(rs.getString("email"))
                    .cpf(rs.getString("cpf")).build();

                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar clientes do nutricionista", e);
        }
        return clientes;
    }

    private List<String> getHorarios(String string) {
        String sql = "SELECT horario FROM nutricionista_horarios WHERE nutricionista_id = ?";
        List<String> horarios = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, string);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    horarios.add(rs.getString("horario"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar horários do nutricionista", e);
        }
        return horarios;
    }

    public void updateNutricionista(Nutri nutricionista) {
        String sql = "UPDATE nutricionistas SET name = ?, contactNumber = ?, email = ?, cpf = ?, crnOrCrm = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nutricionista.getName());
            stmt.setString(2, nutricionista.getContactNumber());
            stmt.setString(3, nutricionista.getEmail());
            stmt.setString(4, nutricionista.getCpf());
            stmt.setString(5, nutricionista.getCrnOrCrm());
            stmt.setString(6, nutricionista.getId());
            stmt.execute();

            // Atualizando clientes e horários
            deleteClientes(nutricionista.getId());
            addClientes(nutricionista);

            deleteHorarios(nutricionista.getId());
            addHorarios(nutricionista);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar nutricionista", e);
        }
    }

    private void deleteClientes(String nutricionistaId) {
        String sql = "DELETE FROM nutricionista_clientes WHERE nutricionista_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nutricionistaId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar clientes do nutricionista", e);
        }
    }

    private void deleteHorarios(String nutricionistaId) {
        String sql = "DELETE FROM nutricionista_horarios WHERE nutricionista_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nutricionistaId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar horários do nutricionista", e);
        }
    }

    public void deleteNutricionista(String id) {
        deleteClientes(id);
        deleteHorarios(id);

        String sql = "DELETE FROM nutricionistas WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar nutricionista", e);
        }
    }
}
