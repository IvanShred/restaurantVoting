package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAllByRestaurant(int restaurantId);

    void update(Meal meal);

    void update(MealTo mealTo);

    Meal create(Meal meal, int restaurantId);
}
