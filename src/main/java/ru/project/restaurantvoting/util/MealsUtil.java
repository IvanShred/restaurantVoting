package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.to.response.MealResponseTo;
import ru.project.restaurantvoting.to.MealTo;

public class MealsUtil {

    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getDateMeal(), newMeal.getPrice());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setDateMeal(mealTo.getDateMeal());
        meal.setPrice(mealTo.getPrice());
        return meal;
    }

    public static MealResponseTo convertToResponse(Meal meal) {
        return new MealResponseTo(meal.getId(), meal.getDateMeal(), meal.getMealType().getDescription(),
                meal.getPrice(), meal.getRestaurant().getId());
    }
}
