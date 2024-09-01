package br.com.evonutri.EvoNutri.Controllers;

import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.DAO.MealsDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealsController {

    private final MealsDAO mealsDAO;

    @Autowired
    public MealsController(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            this.mealsDAO = new MealsDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    @PostMapping
    public void addMeal(@RequestBody Meals meal) {
        try {
            mealsDAO.addMeal(meal);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add meal", e);
        }
    }

    @GetMapping
    public List<Meals> getMeals() {
        try {
            return mealsDAO.getMeals();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get meals", e);
        }
    }

    @GetMapping("/{id}")
    public Meals getMealById(@PathVariable int id) {
        try {
            return mealsDAO.getMealById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get meal by ID", e);
        }
    }

    @PutMapping("/{id}")
    public void updateMeal(@PathVariable int id, @RequestBody Meals meal) {
        try {
            mealsDAO.updateMeal(meal, id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update meal", e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable int id) {
        try {
            mealsDAO.deleteMeal(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete meal", e);
        }
    }
}
