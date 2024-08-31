package br.com.evonutri.EvoNutri.DAO;

import br.com.evonutri.EvoNutri.Model.Dieta;
import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.Model.Nutri;
import br.com.evonutri.EvoNutri.Model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DietaDAO {

    private Connection connection;

    public DietaDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDieta(Dieta dieta) throws SQLException {
        String sqlDieta = "INSERT INTO dieta (id, descricao, nutri_id, cliente_id, data_inicio, data_fim, qtd_ref) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmtDieta = connection.prepareStatement(sqlDieta)) {
            stmtDieta.setString(1, dieta.getId());
            stmtDieta.setString(2, dieta.getDescricao());
            stmtDieta.setString(3, dieta.getNutri().getId());
            stmtDieta.setString(4, dieta.getCliente().getCpf());
            stmtDieta.setString(5, dieta.getDataInicio());
            stmtDieta.setString(6, dieta.getDataFim());
            stmtDieta.setInt(7, dieta.getQtdRef());
            stmtDieta.executeUpdate();

            String sqlMeal = "INSERT INTO meals (dieta_id, horario, calorias, proteinas, gorduras, minerais) VALUES (?, ?, ?, ?, ?, ?)";
            for (Meals meal : dieta.getRefeicoes()) {
                try (PreparedStatement stmtMeal = connection.prepareStatement(sqlMeal)) {
                    stmtMeal.setString(1, dieta.getId());
                    stmtMeal.setString(2, meal.getHorario());
                    stmtMeal.setInt(3, meal.getCalorias());
                    stmtMeal.setInt(4, meal.getProteinas());
                    stmtMeal.setInt(5, meal.getGorduras());
                    stmtMeal.setInt(6, meal.getMinerais());
                    stmtMeal.executeUpdate();
                }
            }
        }
    }

    public List<Dieta> getDietas() throws SQLException {
        List<Dieta> dietasList = new ArrayList<>();
        String sql = "SELECT * FROM dieta";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dieta dieta = new Dieta(
                        rs.getString("descricao"),
                        new NutriDAO().getNutricionistaById(rs.getString("nutri_id")),
                        new ClienteDAO().getClienteByCpf(rs.getString("cliente_id")),
                        rs.getString("data_inicio"),
                        rs.getString("data_fim"),
                        rs.getInt("qtd_ref"),
                        getMealsByDietaId(rs.getString("id"))
                );
                dietasList.add(dieta);
            }
        }
        return dietasList;
    }
// passar o try com o getConnetcion depois para ambos os nutri e client`s
    public Dieta getDietaById(String id) throws SQLException {
        Dieta dieta = null;
        String sql = "SELECT * FROM dieta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dieta = new Dieta(
                            rs.getString("descricao"),
                            new NutriDAO().getNutricionistaById(rs.getString("nutri_id")),
                            new ClienteDAO().getClienteByCpf(rs.getString("cliente_id")),
                            rs.getString("data_inicio"),
                            rs.getString("data_fim"),
                            rs.getInt("qtd_ref"),
                            getMealsByDietaId(rs.getString("id"))
                    );
                }
            }
        }
        return dieta;
    }

    public void updateDieta(Dieta dieta) throws SQLException {
        String sql = "UPDATE dieta SET descricao = ?, nutri_id = ?, cliente_id = ?, data_inicio = ?, data_fim = ?, qtd_ref = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dieta.getDescricao());
            stmt.setString(2, dieta.getNutri().getId());
            stmt.setString(3, dieta.getCliente().getCpf());
            stmt.setString(4, dieta.getDataInicio());
            stmt.setString(5, dieta.getDataFim());
            stmt.setInt(6, dieta.getQtdRef());
            stmt.setString(7, dieta.getId());
            stmt.executeUpdate();

            // Atualizar as refeições
            String sqlDeleteMeals = "DELETE FROM meals WHERE dieta_id = ?";
            try (PreparedStatement stmtDeleteMeals = connection.prepareStatement(sqlDeleteMeals)) {
                stmtDeleteMeals.setString(1, dieta.getId());
                stmtDeleteMeals.executeUpdate();
            }

            String sqlMeal = "INSERT INTO meals (dieta_id, horario, calorias, proteinas, gorduras, minerais) VALUES (?, ?, ?, ?, ?, ?)";
            for (Meals meal : dieta.getRefeicoes()) {
                try (PreparedStatement stmtMeal = connection.prepareStatement(sqlMeal)) {
                    stmtMeal.setString(1, dieta.getId());
                    stmtMeal.setString(2, meal.getHorario());
                    stmtMeal.setInt(3, meal.getCalorias());
                    stmtMeal.setInt(4, meal.getProteinas());
                    stmtMeal.setInt(5, meal.getGorduras());
                    stmtMeal.setInt(6, meal.getMinerais());
                    stmtMeal.executeUpdate();
                }
            }
        }
    }

    public void deleteDieta(String id) throws SQLException {
        String sqlDeleteMeals = "DELETE FROM meals WHERE dieta_id = ?";
        try (PreparedStatement stmtDeleteMeals = connection.prepareStatement(sqlDeleteMeals)) {
            stmtDeleteMeals.setString(1, id);
            stmtDeleteMeals.executeUpdate();
        }

        String sql = "DELETE FROM dieta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    private List<Meals> getMealsByDietaId(String dietaId) throws SQLException {
        List<Meals> mealsList = new ArrayList<>();
        String sql = "SELECT * FROM meals WHERE dieta_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dietaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Meals meal = new Meals(
                            rs.getString("horario"),
                            rs.getInt("calorias"),
                            rs.getInt("proteinas"),
                            rs.getInt("gorduras"),
                            rs.getInt("minerais")
                    );
                    mealsList.add(meal);
                }
            }
        }
        return mealsList;
    }
}
