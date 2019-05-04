package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurantTo;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAllByRestaurant(int restaurantId);

    List<MealsRestaurantTo> getMenu();

    void update(Meal meal);

    void update(MealTo mealTo);

    Meal create(Meal meal);
}
