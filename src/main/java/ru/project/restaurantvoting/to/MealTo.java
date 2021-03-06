package ru.project.restaurantvoting.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MealTo extends BaseTo {

    @NotNull
    private LocalDate dateMeal;

    private int mealTypeId;

    @Range(min = 10, max = 3000)
    private int price;

    private int restaurantId;

    public MealTo() {
    }

    public MealTo(Integer id, LocalDate dateMeal, int mealTypeId, int price, int restaurantId) {
        super(id);
        this.dateMeal = dateMeal;
        this.mealTypeId = mealTypeId;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDateMeal() {
        return dateMeal;
    }

    public void setDateMeal(LocalDate dateMeal) {
        this.dateMeal = dateMeal;
    }

    public int getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateMeal=" + dateMeal +
                ", mealTypeId='" + mealTypeId + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
