package com.evonutri.backend.backend.Controllers;

import br.com.evonutri.EvoNutri.Controllers.ClienteController;
import br.com.evonutri.EvoNutri.DAO.ClienteDAO;
import br.com.evonutri.EvoNutri.Model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteDAO clienteDAO;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente("John Doe", "123 Main St", "555-5555", "30", 70.0, "john@example.com", "12345678901");
    }

    @Test
    void addCliente_ShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente adicionado com sucesso"));
    }

    @Test
    void getAllClientes_ShouldReturnList() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        given(clienteDAO.getAllClientes()).willReturn(clientes);

        mockMvc.perform(get("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].endereco").value("123 Main St"));
    }

    @Test
    void getClienteByCpf_ShouldReturnCliente() throws Exception {
        given(clienteDAO.getClienteByCpf("12345678901")).willReturn(cliente);

        mockMvc.perform(get("/api/clientes/12345678901")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.endereco").value("123 Main St"));
    }

    @Test
    void getClienteByCpf_ShouldReturnNotFound() throws Exception {
        given(clienteDAO.getClienteByCpf("00000000000")).willReturn(null);

        mockMvc.perform(get("/api/clientes/00000000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCliente_ShouldReturnOk() throws Exception {
        Cliente updatedCliente = new Cliente("John Doe", "123 Main St", "555-5555", "30", 75.0, "john.doe@example.com", "12345678901");
        
        mockMvc.perform(put("/api/clientes/12345678901")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCliente)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente atualizado com sucesso"));

        then(clienteDAO).should().updateCliente(updatedCliente);
    }

    @Test
    void deleteCliente_ShouldReturnOk() throws Exception {
        mockMvc.perform(delete("/api/clientes/12345678901")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente deletado com sucesso"));

        then(clienteDAO).should().deleteCliente("12345678901");
    }
}
