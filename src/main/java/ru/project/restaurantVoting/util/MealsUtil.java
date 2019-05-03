package ru.project.restaurantVoting.util;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurantTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static List<MealsRestaurantTo> getAllForRestaurants(List<Meal> meals) {
        Map<Integer, List<Meal>> map = meals.stream()
                .collect(Collectors.groupingBy(/*Meal::getRestaurantId*/meal -> meal.getRestaurant().getId()));

        List<MealsRestaurantTo> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Meal>> entry : map.entrySet()) {
            result.add(new MealsRestaurantTo(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getDateMeal(), newMeal.getDescription(), newMeal.getPrice(), new Restaurant(newMeal.getRestaurantId()));
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setDateMeal(mealTo.getDateMeal());
        meal.setDescription(mealTo.getDescription());
        meal.setPrice(mealTo.getPrice());
        return meal;
    }

//    public static MealsRestaurantTo getForRestaurant(Restaurant restaurant, List<Meal> meals) {
//        return new MealsRestaurantTo(restaurant, meals);
//    }
}
