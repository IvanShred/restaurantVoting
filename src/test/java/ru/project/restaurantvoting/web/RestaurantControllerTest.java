package ru.project.restaurantvoting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.service.RestaurantService;
import ru.project.restaurantvoting.web.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.project.restaurantvoting.RestaurantTestData.*;
import static ru.project.restaurantvoting.TestUtil.*;

class RestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService service;

    private static final String REST_URL = RestaurantController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT));
    }

    @Test
    void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test
    void testGetMenu() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL + "menu")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Restaurant> restaurants = readListFromJsonMvcResult(mvcResult, Restaurant.class);

        assertEquals(2, restaurants.size());

        assertTrue(restaurants.stream().allMatch(restaurant -> restaurant.getMeals().size() == 4));
    }

    @Test
    void testGetWithMeals() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL + RESTAURANT_ID + "/menu")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        Restaurant restaurant = readFromJsonMvcResult(mvcResult, Restaurant.class);

        assertNotNull(restaurant);
        assertEquals(4, restaurant.getMeals().size());
    }

    @Test
    void testGetAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Restaurant> restaurants = readListFromJsonMvcResult(mvcResult, Restaurant.class);

        assertEquals(2, restaurants.size());
    }

}