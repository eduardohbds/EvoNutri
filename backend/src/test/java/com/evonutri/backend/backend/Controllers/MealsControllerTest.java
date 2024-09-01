package com.evonutri.backend.backend.Controllers;

import br.com.evonutri.EvoNutri.Model.Meals;
import br.com.evonutri.EvoNutri.Controllers.MealsController;
import br.com.evonutri.EvoNutri.DAO.MealsDAO;
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

@WebMvcTest(MealsController.class)
public class MealsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealsDAO mealsDAO;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMeal() throws Exception {
        Meals meal = new Meals("08:00", 500, 20, 10, 5);

        mockMvc.perform(post("/api/meals")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMeals() throws Exception {
        Meals meal1 = new Meals("08:00", 500, 20, 10, 5);
        Meals meal2 = new Meals("12:00", 600, 25, 15, 6);
        List<Meals> mealsList = Arrays.asList(meal1, meal2);

        given(mealsDAO.getMeals()).willReturn(mealsList);

        mockMvc.perform(get("/api/meals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].horario").value("08:00"))
                .andExpect(jsonPath("$[1].calorias").value(600));
    }

    @Test
    public void testGetMealById() throws Exception {
        Meals meal = new Meals("08:00", 500, 20, 10, 5);
        int id = 1;

        given(mealsDAO.getMealById(id)).willReturn(meal);

        mockMvc.perform(get("/api/meals/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.horario").value("08:00"))
                .andExpect(jsonPath("$.calorias").value(500));
    }

    @Test
    public void testUpdateMeal() throws Exception {
        Meals meal = new Meals("08:00", 500, 20, 10, 5);
        int id = 1;

        willDoNothing().given(mealsDAO).updateMeal(meal, id);

        mockMvc.perform(put("/api/meals/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMeal() throws Exception {
        int id = 1;

        willDoNothing().given(mealsDAO).deleteMeal(id);

        mockMvc.perform(delete("/api/meals/{id}", id))
                .andExpect(status().isOk());
    }
}
