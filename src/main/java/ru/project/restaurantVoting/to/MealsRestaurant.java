package ru.project.restaurantVoting.to;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;

import java.util.List;

public class MealsRestaurant {

    private Restaurant restaurant;
    private List<Meal> meals;

    public MealsRestaurant(Restaurant restaurant, List<Meal> meals) {
        this.restaurant = restaurant;
        this.meals = meals;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "MealsRestaurant{" +
                "restaurant=" + restaurant +
                ", meals=" + meals +
                '}';
    }
}
