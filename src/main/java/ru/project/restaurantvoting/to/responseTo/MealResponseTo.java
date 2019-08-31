package ru.project.restaurantvoting.to.responseTo;

import ru.project.restaurantvoting.to.BaseTo;

import java.time.LocalDate;

public class MealResponseTo extends BaseTo {

    private LocalDate dateMeal;

    private String description;

    private int price;

    private int restaurantId;

    public MealResponseTo() {
    }

    public MealResponseTo(Integer id, LocalDate dateMeal, String description, int price, int restaurantId) {
        super(id);
        this.dateMeal = dateMeal;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDateMeal() {
        return dateMeal;
    }

    public void setDateMeal(LocalDate dateMeal) {
        this.dateMeal = dateMeal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "MealResponseTo{" +
                "dateMeal=" + dateMeal +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
