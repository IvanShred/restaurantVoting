package ru.project.restaurantVoting.util;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.to.MealsRestaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static List<MealsRestaurant> getAllForRestaurants(List<Meal> meals) {
        Map<Restaurant, List<Meal>> map = meals.stream()
                .collect(Collectors.groupingBy(Meal::getRestaurant));

        List<MealsRestaurant> result = new ArrayList<>();
        for (Map.Entry<Restaurant, List<Meal>> entry : map.entrySet()) {
            result.add(new MealsRestaurant(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public static MealsRestaurant getForRestaurant(Restaurant restaurant, List<Meal> meals) {
        return new MealsRestaurant(restaurant, meals);
    }
}
