package ru.project.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"date_meal", "restaurant_id", "meal_id"},
                name = "meals_unique_datemeal_mealid_restaurantid_idx")})
public class Meal extends AbstractBaseEntity {

    @Column(name = "date_meal", nullable = false)
    @NotNull
    private LocalDate dateMeal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id", nullable = false)
    private MealType mealType;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 3000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(Integer id, LocalDate dateMeal, int price) {
        super(id);
        this.dateMeal = dateMeal;
        this.price = price;
    }

    public Meal(Integer id, LocalDate dateMeal, MealType mealType, int price, Restaurant restaurant) {
        super(id);
        this.dateMeal = dateMeal;
        this.mealType = mealType;
        this.price = price;
        this.restaurant = restaurant;
    }

    public LocalDate getDateMeal() {
        return dateMeal;
    }

    public MealType getMealType() {
        return mealType;
    }

    public int getPrice() {
        return price;
    }


    public void setDateMeal(LocalDate dateMeal) {
        this.dateMeal = dateMeal;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateMeal=" + dateMeal +
                ", mealType='" + mealType + '\'' +
                ", price=" + price +
                '}';
    }
}
