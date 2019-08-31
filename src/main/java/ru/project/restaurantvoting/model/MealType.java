package ru.project.restaurantvoting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "s_meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = "description", name = "s_meals_unique_description_idx")})
public class MealType extends AbstractBaseEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    private String description;

    public MealType() {
    }

    public MealType(Integer id) {
        this(id, null);
    }

    public MealType(Integer id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MealType{" +
                "description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
