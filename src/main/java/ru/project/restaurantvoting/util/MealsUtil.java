package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.to.MealTo;

public class MealsUtil {

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
