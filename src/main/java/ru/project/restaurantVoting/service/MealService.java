package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurant;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAllByRestaurant(int restaurantId);

    List<MealsRestaurant> getAll();

    void update(MealTo mealTo);

    Meal create(Meal meal);
}
