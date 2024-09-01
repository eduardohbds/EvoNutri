package br.com.evonutri.EvoNutri.Controllers;

import br.com.evonutri.EvoNutri.DAO.ClienteDAO;
import br.com.evonutri.EvoNutri.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {
        try {
            clienteDAO.addCliente(cliente);
            return ResponseEntity.ok("Cliente adicionado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clientes = clienteDAO.getAllClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> getClienteByCpf(@PathVariable("cpf") String cpf) {
        try {
            Cliente cliente = clienteDAO.getClienteByCpf(cpf);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> updateCliente(@PathVariable("cpf") String cpf, @RequestBody Cliente cliente) {
        try {
            cliente.setCpf(cpf); // Ensure the CPF is set to the path variable value
            clienteDAO.updateCliente(cliente);
            return ResponseEntity.ok("Cliente atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteCliente(@PathVariable("cpf") String cpf) {
        try {
            clienteDAO.deleteCliente(cpf);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
