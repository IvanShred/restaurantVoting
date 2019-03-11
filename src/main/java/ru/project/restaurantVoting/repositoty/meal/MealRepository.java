package ru.project.restaurantVoting.repositoty.meal;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;

import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    List<Meal> getAll();

    List<Meal> getAllByRestaurant(Restaurant restaurant);
}
