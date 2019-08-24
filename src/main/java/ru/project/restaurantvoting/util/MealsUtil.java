package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.to.MealsRestaurantTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static List<MealsRestaurantTo> getAllForRestaurants(List<Meal> meals) {
        Map<Integer, List<Meal>> map = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getRestaurant().getId()));

        List<MealsRestaurantTo> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Meal>> entry : map.entrySet()) {
            result.add(new MealsRestaurantTo(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getDateMeal(), newMeal.getDescription(), newMeal.getPrice());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setDateMeal(mealTo.getDateMeal());
        meal.setDescription(mealTo.getDescription());
        meal.setPrice(mealTo.getPrice());
        return meal;
    }
}
