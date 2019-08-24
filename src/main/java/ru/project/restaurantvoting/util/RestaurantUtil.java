package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.to.RestaurantTo;

public class RestaurantUtil {

    public static Restaurant createNewFromTo(RestaurantTo newRestaurant) {
        return new Restaurant(null, newRestaurant.getName(), newRestaurant.getAddress());
    }

}
