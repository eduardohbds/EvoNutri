package com.evonutri.backend.backend.Controllers;

import br.com.evonutri.EvoNutri.Controllers.NutriController;
import br.com.evonutri.EvoNutri.DAO.NutriDAO;
import br.com.evonutri.EvoNutri.Model.Nutri;
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

@WebMvcTest(NutriController.class)
public class NutriControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NutriDAO nutriDAO;

    @Autowired
    private ObjectMapper objectMapper;

    private Nutri nutricionista;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nutricionista = new Nutri("123", "Dr. John", "555-5555", "john.doe@example.com");
    }

    @Test
    void addNutricionista_ShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/nutricionistas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nutricionista)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllNutricionistas_ShouldReturnList() throws Exception {
        List<Nutri> nutricionistas = new ArrayList<>();
        nutricionistas.add(nutricionista);
        given(nutriDAO.getAllNutricionistas()).willReturn(nutricionistas);

        mockMvc.perform(get("/nutricionistas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("123"))
                .andExpect(jsonPath("$[0].name").value("Dr. John"));
    }

    @Test
    void getNutricionistaById_ShouldReturnNutricionista() throws Exception {
        given(nutriDAO.getNutricionistaById("123")).willReturn(nutricionista);

        mockMvc.perform(get("/nutricionistas/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("Dr. John"));
    }

    @Test
    void getNutricionistaById_ShouldReturnNotFound() throws Exception {
        given(nutriDAO.getNutricionistaById("999")).willReturn(null);

        mockMvc.perform(get("/nutricionistas/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Nutricionista not found"));
    }

    @Test
    void updateNutricionista_ShouldReturnOk() throws Exception {
        mockMvc.perform(put("/nutricionistas/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nutricionista)))
                .andExpect(status().isOk());

        then(nutriDAO).should().updateNutricionista(nutricionista);
    }

    @Test
    void deleteNutricionista_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/nutricionistas/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        then(nutriDAO).should().deleteNutricionista("123");
    }
}
