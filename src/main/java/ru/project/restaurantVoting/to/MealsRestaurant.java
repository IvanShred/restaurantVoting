package ru.project.restaurantVoting.to;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;

import java.util.List;

public class MealsRestaurant {

    private int restaurantId;
    private List<Meal> meals;

    public MealsRestaurant(int restaurantId, List<Meal> meals) {
        this.restaurantId = restaurantId;
        this.meals = meals;
    }

    public int getRestaurant() {
        return restaurantId;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "MealsRestaurant{" +
                "restaurantID=" + restaurantId +
                ", meals=" + meals +
                '}';
    }
}
