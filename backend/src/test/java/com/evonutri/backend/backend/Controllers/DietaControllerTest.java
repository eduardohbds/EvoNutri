package com.evonutri.backend.backend.Controllers;

import br.com.evonutri.EvoNutri.Model.Cliente;
import br.com.evonutri.EvoNutri.Model.Dieta;
import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.Model.Nutri;
import br.com.evonutri.EvoNutri.Controllers.DietaController;
import br.com.evonutri.EvoNutri.DAO.DietaDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(DietaController.class)
public class DietaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DietaDAO dietaDAO;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDieta() throws Exception {
        Dieta dieta = new Dieta(
                "Descrição da dieta",
                new Nutri("Nutricionista", "123456789", "email@domain.com", "12345678901", "1234"),
                new Cliente("Cliente", "Endereço", "123456789", "30", 70.0, "cliente@domain.com", "98765432100"),
                "2024-01-01",
                "2024-12-31",
                5,
                Arrays.asList(new Meals("08:00", 500, 20, 10, 5))
        );

        mockMvc.perform(post("/api/dietas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dieta)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDietas() throws Exception {
        Dieta dieta1 = new Dieta(
                "Descrição da dieta 1",
                new Nutri("Nutricionista 1", "123456789", "email1@domain.com", "12345678901", "1234"),
                new Cliente("Cliente 1", "Endereço 1", "123456789", "30", 70.0, "cliente1@domain.com", "98765432100"),
                "2024-01-01",
                "2024-06-30",
                5,
                Arrays.asList(new Meals("08:00", 500, 20, 10, 5))
        );
        Dieta dieta2 = new Dieta(
                "Descrição da dieta 2",
                new Nutri("Nutricionista 2", "987654321", "email2@domain.com", "12345678902", "5678"),
                new Cliente("Cliente 2", "Endereço 2", "987654321", "35", 75.0, "cliente2@domain.com", "01234567890"),
                "2024-07-01",
                "2024-12-31",
                7,
                Arrays.asList(new Meals("12:00", 600, 25, 15, 6))
        );
        List<Dieta> dietasList = Arrays.asList(dieta1, dieta2);

        given(dietaDAO.getDietas()).willReturn(dietasList);

        mockMvc.perform(get("/api/dietas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Descrição da dieta 1"))
                .andExpect(jsonPath("$[1].descricao").value("Descrição da dieta 2"));
    }

    @Test
    public void testGetDietaById() throws Exception {
        Dieta dieta = new Dieta(
                "Descrição da dieta",
                new Nutri("Nutricionista", "123456789", "email@domain.com", "12345678901", "1234"),
                new Cliente("Cliente", "Endereço", "123456789", "30", 70.0, "cliente@domain.com", "98765432100"),
                "2024-01-01",
                "2024-12-31",
                5,
                Arrays.asList(new Meals("08:00", 500, 20, 10, 5))
        );
        String id = "some-id";

        given(dietaDAO.getDietaById(id)).willReturn(dieta);

        mockMvc.perform(get("/api/dietas/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Descrição da dieta"));
    }

    @Test
    public void testUpdateDieta() throws Exception {
        Dieta dieta = new Dieta(
                "Descrição da dieta atualizada",
                new Nutri("Nutricionista Atualizado", "123456789", "email@domain.com", "12345678901", "1234"),
                new Cliente("Cliente Atualizado", "Endereço Atualizado", "123456789", "30", 70.0, "cliente@domain.com", "98765432100"),
                "2024-01-01",
                "2024-12-31",
                6,
                Arrays.asList(new Meals("09:00", 550, 22, 12, 7))
        );
        String id = "some-id";

        willDoNothing().given(dietaDAO).updateDieta(dieta);

        mockMvc.perform(put("/api/dietas/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dieta)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDieta() throws Exception {
        String id = "some-id";

        willDoNothing().given(dietaDAO).deleteDieta(id);

        mockMvc.perform(delete("/api/dietas/{id}", id))
                .andExpect(status().isOk());
    }
}
