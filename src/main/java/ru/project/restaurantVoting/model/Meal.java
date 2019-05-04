package ru.project.restaurantVoting.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.restaurantVoting.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    @Column(name = "date_meal", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateUtil.DATE_PATTERN)
    private LocalDate dateMeal;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 3000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY/*EAGER*/)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(LocalDate dateMeal, String description, int price, Restaurant restaurant) {
        this(null, dateMeal, description, price, restaurant);
    }

    public Meal(Integer id, LocalDate dateMeal, String description, int price) {
        super(id);
        this.dateMeal = dateMeal;
        this.description = description;
        this.price = price;
    }

    public Meal(Integer id, LocalDate dateMeal, String description, int price, Restaurant restaurant) {
        super(id);
        this.dateMeal = dateMeal;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public LocalDate getDateMeal() {
        return dateMeal;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }


    public void setDateMeal(LocalDate dateMeal) {
        this.dateMeal = dateMeal;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
