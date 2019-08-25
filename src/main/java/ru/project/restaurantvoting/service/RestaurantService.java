package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.to.RestaurantTo;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    void update(RestaurantTo restaurantTo, int restaurantId);

    Restaurant create(Restaurant restaurant);

    Restaurant getWithMeals(int id);

    List<Restaurant> getMenu(LocalDate date);

    List<Restaurant> getAll();
}
