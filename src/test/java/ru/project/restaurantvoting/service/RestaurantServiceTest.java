package ru.project.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.to.RestaurantTo;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.project.restaurantvoting.MealTestData.MEALS;
import static ru.project.restaurantvoting.MealTestData.MEALS2;
import static ru.project.restaurantvoting.MealTestData.assertMatch;
import static ru.project.restaurantvoting.RestaurantTestData.assertMatch;
import static ru.project.restaurantvoting.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID);

        assertMatch(restaurant, RESTAURANT);
    }

    @Test
    void delete() {
        service.delete(RESTAURANT_ID);

        assertThrows(NotFoundException.class, () ->
                service.get(RESTAURANT_ID));
    }

    @Test
    void update() {
        RestaurantTo restaurantTo = new RestaurantTo(null, "name", "address");

        service.update(restaurantTo, RESTAURANT_ID);

        Restaurant restaurant = service.get(RESTAURANT_ID);

        assertNotNull(restaurant);
        assertEquals("name", restaurant.getName());
        assertEquals("address", restaurant.getAddress());
    }

    @Test
    void create() {
        Restaurant newRestaurant = getCreated();

        Restaurant created = service.create(newRestaurant);

        newRestaurant.setId(created.getId());

        assertMatch(newRestaurant, created);
    }

    @Test
    void getWithMeals() {
        Restaurant restaurant = service.getWithMeals(RESTAURANT_ID);

        assertMatch(restaurant, RESTAURANT);
        assertMatch(restaurant.getMeals(), MEALS);
    }

    @Test
    void getMenu() {
        List<Restaurant> menu = service.getMenu(LocalDate.now());

        Restaurant restaurant = menu.get(0);
        Restaurant restaurant2 = menu.get(1);

        assertMatch(restaurant, RESTAURANT);
        assertMatch(restaurant.getMeals(), MEALS);
        assertMatch(restaurant2, RESTAURANT2);
        assertMatch(restaurant2.getMeals(), MEALS2);
    }

    @Test
    void getAll() {
        List<Restaurant> restaurants = service.getAll();

        restaurants.stream().filter(restaurant -> restaurant.getId() == RESTAURANT_ID)
                .forEach(restaurant -> assertMatch(restaurant, RESTAURANT));

        restaurants.stream().filter(restaurant -> restaurant.getId() == RESTAURANT_ID + 1)
                .forEach(restaurant -> assertMatch(restaurant, RESTAURANT2));
    }
}