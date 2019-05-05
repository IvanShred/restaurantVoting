package ru.project.restaurantvoting.to;

import ru.project.restaurantvoting.model.Meal;

import java.util.List;

public class MealsRestaurantTo {

    private int restaurantId;
    private List<Meal> meals;

    public MealsRestaurantTo(int restaurantId, List<Meal> meals) {
        this.restaurantId = restaurantId;
        this.meals = meals;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "MealsRestaurantTo{" +
                "restaurantID=" + restaurantId +
                ", meals=" + meals +
                '}';
    }
}
