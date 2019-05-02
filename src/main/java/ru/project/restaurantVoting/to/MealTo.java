package ru.project.restaurantVoting.to;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.project.restaurantVoting.util.DateUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class MealTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @DateTimeFormat(pattern = DateUtil.DATE_PATTERN)
    private LocalDate dateMeal;

    @NotBlank
    @Size(min = 3, max = 200)
    private String description;

    @Range(min = 10, max = 3000)
    private int price;

    private int restaurantId;

    public MealTo() {
    }

    public MealTo(Integer id, LocalDate dateMeal, String description, int price, int restaurantId) {
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
        return "MealTo{" +
                "dateMeal=" + dateMeal +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
