package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAllByRestaurant(Restaurant restaurant);

    List<Meal> getAll();

    void update(Meal meal);

    Meal create(Meal meal);
}
