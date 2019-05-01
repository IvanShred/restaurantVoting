package ru.project.restaurantVoting.repository.meal;

import ru.project.restaurantVoting.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    List<Meal> getAll(LocalDate date);

    List<Meal> getAllByRestaurant(int restaurantId, LocalDate date);
}
