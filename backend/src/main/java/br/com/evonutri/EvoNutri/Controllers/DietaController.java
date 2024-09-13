package br.com.evonutri.EvoNutri.Controllers;

import br.com.evonutri.EvoNutri.Model.Dieta;
import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.Model.Nutri;
import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.DAO.DietaDAO;
import br.com.evonutri.EvoNutri.DAO.NutriDAO;
import br.com.evonutri.EvoNutri.DAO.ClienteDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/dietas")
public class DietaController {

    private final DietaDAO dietaDAO;
    private ClienteDAO clienteDAO;
    private NutriDAO nutriDAO;

    public DietaController(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            this.dietaDAO = new DietaDAO(connection);
            this.nutriDAO = new NutriDAO();
            this.clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    @PostMapping
    public void addDieta(@RequestBody Dieta dieta) {
        try {
            dietaDAO.addDieta(dieta);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add dieta", e);
        }
    }

    @GetMapping
    public List<Dieta> getDietas() {
        try {
            return dietaDAO.getDietas();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get dietas", e);
        }
    }

    @GetMapping("/{id}")
    public Dieta getDietaById(@PathVariable String id) {
        try {
            return dietaDAO.getDietaById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get dieta by ID", e);
        }
    }

    @PutMapping("/{id}")
    public void updateDieta(@PathVariable String id, @RequestBody Dieta dieta) {
        if (!id.equals(dieta.getId())) {
            throw new IllegalArgumentException("ID in path does not match ID in body");
        }
        try {
            dietaDAO.updateDieta(dieta);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update dieta", e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDieta(@PathVariable String id) {
        try {
            dietaDAO.deleteDieta(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete dieta", e);
        }
    }
}
