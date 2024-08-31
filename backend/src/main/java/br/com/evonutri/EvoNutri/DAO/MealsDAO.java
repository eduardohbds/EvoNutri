package br.com.evonutri.EvoNutri.DAO;

import br.com.evonutri.EvoNutri.Model.Meals;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealsDAO {

    private Connection connection;

    public MealsDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMeal(Meals meal) throws SQLException {
        String sql = "INSERT INTO meals (horario, calorias, proteinas, gorduras, minerais) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, meal.getHorario());
            stmt.setInt(2, meal.getCalorias());
            stmt.setInt(3, meal.getProteinas());
            stmt.setInt(4, meal.getGorduras());
            stmt.setInt(5, meal.getMinerais());
            stmt.executeUpdate();
        }
    }

    public List<Meals> getMeals() throws SQLException {
        List<Meals> mealsList = new ArrayList<>();
        String sql = "SELECT * FROM meals";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
        return mealsList;
    }

    public Meals getMealById(int id) throws SQLException {
        Meals meal = null;
        String sql = "SELECT * FROM meals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    meal = new Meals(
                            rs.getString("horario"),
                            rs.getInt("calorias"),
                            rs.getInt("proteinas"),
                            rs.getInt("gorduras"),
                            rs.getInt("minerais")
                    );
                }
            }
        }
        return meal;
    }

    public void updateMeal(Meals meal, int id) throws SQLException {
        String sql = "UPDATE meals SET horario = ?, calorias = ?, proteinas = ?, gorduras = ?, minerais = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, meal.getHorario());
            stmt.setInt(2, meal.getCalorias());
            stmt.setInt(3, meal.getProteinas());
            stmt.setInt(4, meal.getGorduras());
            stmt.setInt(5, meal.getMinerais());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }

    public void deleteMeal(int id) throws SQLException {
        String sql = "DELETE FROM meals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
