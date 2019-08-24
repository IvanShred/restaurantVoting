package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.to.RestaurantTo;
import ru.project.restaurantvoting.util.exception.NotFoundException;

public interface RestaurantService {

    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    void update(RestaurantTo restaurantTo, int restaurantId);

    Restaurant create(Restaurant restaurant);

    Restaurant getWithMeals(int id);
}
