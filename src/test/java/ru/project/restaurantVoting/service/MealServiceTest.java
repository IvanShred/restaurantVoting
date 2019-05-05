package ru.project.restaurantVoting.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.to.MealsRestaurantTo;

import java.util.List;

import static ru.project.restaurantVoting.MealTestData.*;
import static ru.project.restaurantVoting.model.AbstractBaseEntity.START_SEQ;

class MealServiceTest extends AbstractServiceTest{

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
        Meal actual = service.get(MEAL1_ID);
        assertMatch(actual, MEAL1);
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
    void getMenu() throws Exception {
        List<MealsRestaurantTo> menu = service.getMenu();
        assertMatch(menu.get(1).getMeals(), MEAL1, MEAL2, MEAL3, MEAL4);
        assertMatch(menu.get(0).getMeals(), MEAL5, MEAL6, MEAL7, MEAL8);
    }

    @Test
    void update() throws Exception {
        Meal updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(MEAL1_ID), updated);
    }

    @Test
    void create() throws Exception {
        Meal newMeal = getCreated();
        Meal created = service.create(newMeal, RESTAURANT_ID);
        newMeal.setId(created.getId());
        assertMatch(newMeal, created);
        assertMatch(service.getAllByRestaurant(START_SEQ + 2), newMeal, MEAL1, MEAL2, MEAL3, MEAL4);
    }
}