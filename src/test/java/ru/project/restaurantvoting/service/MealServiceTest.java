package ru.project.restaurantvoting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.to.response.MealResponseTo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.project.restaurantvoting.MealTestData.*;
import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("menu").clear();
    }

    @Test
    void get() throws Exception {
        MealResponseTo actual = service.get(MEAL1_ID);

        assertNotNull(actual);
        assertEquals(MEAL1_ID, actual.getId());
    }

    @Test
    void delete() throws Exception {
        service.delete(MEAL1_ID);
        assertMatch(service.getAllByRestaurant(START_SEQ + 2), MEAL2, MEAL3, MEAL4);
    }

    @Test
    void getAllByRestaurant() throws Exception {
        assertMatch(service.getAllByRestaurant(START_SEQ + 2), MEALS);
    }

    @Test
    void update() throws Exception {
        MealTo updated = getUpdated();

        service.update(updated);

        MealResponseTo mealResponseTo = service.get(updated.getId());

        assertNotNull(mealResponseTo);
        assertEquals(MEAL_TYPE3.getDescription(), mealResponseTo.getDescription());
        assertEquals(150, mealResponseTo.getPrice());
        assertEquals(RESTAURANT2.getId(), mealResponseTo.getRestaurantId());
    }

    @Test
    void create() throws Exception {
        Meal newMeal = getCreated();
        MealResponseTo created = service.create(newMeal, MEAL_TYPE5.getId(), RESTAURANT_ID);

        assertNotNull(created);
        assertEquals(50, created.getPrice());
        assertEquals(MEAL_TYPE5.getDescription(), created.getDescription());
        assertEquals(LocalDate.now(), created.getDateMeal());
        assertEquals(RESTAURANT_ID, created.getRestaurantId());
    }
}