//package ru.project.restaurantvoting.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import ru.project.restaurantvoting.model.Meal;
//
//import static ru.project.restaurantvoting.MealTestData.*;
//import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;
//
//class MealServiceTest extends AbstractServiceTest{
//
//    @Autowired
//    protected MealService service;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        cacheManager.getCache("menu").clear();
//    }
//
//    @Test
//    void get() throws Exception {
//        Meal actual = service.get(MEAL1_ID);
//        assertMatch(actual, MEAL1);
//    }
//
//    @Test
//    void delete() throws Exception {
//        service.delete(MEAL1_ID);
//        assertMatch(service.getAllByRestaurant(START_SEQ + 2), MEAL2, MEAL3, MEAL4);
//    }
//
//    @Test
//    void getAllByRestaurant() throws Exception {
//        assertMatch(service.getAllByRestaurant(START_SEQ + 2), MEALS);
//    }
//
//    @Test
//    void update() throws Exception {
//        Meal updated = getUpdated();
//        service.update(updated);
//        assertMatch(service.get(MEAL1_ID), updated);
//    }
//
//    @Test
//    void create() throws Exception {
//        Meal newMeal = getCreated();
//        Meal created = service.create(newMeal, RESTAURANT_ID);
//        newMeal.setId(created.getId());
//        assertMatch(newMeal, created);
//        assertMatch(service.getAllByRestaurant(START_SEQ + 2), newMeal, MEAL1, MEAL2, MEAL3, MEAL4);
//    }
//}