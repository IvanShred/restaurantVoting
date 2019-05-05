package ru.project.restaurantvoting.repository.meal;

import ru.project.restaurantvoting.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, int restaurantId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    List<Meal> getAll(LocalDate date);

    List<Meal> getAllByRestaurant(int restaurantId, LocalDate date);
}
