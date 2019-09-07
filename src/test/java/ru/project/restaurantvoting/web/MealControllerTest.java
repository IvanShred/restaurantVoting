package ru.project.restaurantvoting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.service.MealService;
import ru.project.restaurantvoting.to.responseTo.MealResponseTo;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.project.restaurantvoting.MealTestData.*;
import static ru.project.restaurantvoting.TestUtil.*;

class MealControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService service;

    private static final String REST_URL = MealController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL + MEAL1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        MealResponseTo returned = readFromJsonMvcResult(mvcResult, MealResponseTo.class);

        assertNotNull(returned);
        assertEquals(LocalDate.of(2019, 9, 7), returned.getDateMeal());
        assertEquals("Кофе", returned.getDescription());
        assertEquals(60, returned.getPrice());
        assertEquals(100002, returned.getRestaurantId());
    }

    @Test
    void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCreate() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dateMeal\": \"2019-05-05\",\"mealTypeId\": 100004,\"price\": 70,\"restaurantId\": 100003}")
                .with(userHttpBasic(ADMIN)));

        MealResponseTo returned = readFromJson(action, MealResponseTo.class);

        assertNotNull(returned);
        assertEquals(LocalDate.of(2019, 5, 5), returned.getDateMeal());
        assertEquals("Кофе", returned.getDescription());
        assertEquals(70, returned.getPrice());
        assertEquals(100003, returned.getRestaurantId());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertMatch(service.getAllByRestaurant(RESTAURANT_ID), MEAL2, MEAL3, MEAL4);
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dateMeal\": \"2019-08-09\",\"mealTypeId\": 100008,\"price\": 26,\"restaurantId\": 100003}")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        MealResponseTo mealResponseTo = service.get(MEAL1_ID);

        assertNotNull(mealResponseTo);
        assertEquals(LocalDate.of(2019, 8, 9), mealResponseTo.getDateMeal());
        assertEquals("Чай", mealResponseTo.getDescription());
        assertEquals(26, mealResponseTo.getPrice());
        assertEquals(100003, mealResponseTo.getRestaurantId());
    }

    @Test
    void testGetByRestaurantId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL + "by")
                .param("restaurantId", "100002")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andReturn();

        List<Meal> meals = readListFromJsonMvcResult(mvcResult, Meal.class);

        assertEquals(4, meals.size());
    }
}