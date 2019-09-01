package ru.project.restaurantvoting;

import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.model.MealType;
import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.to.MealTo;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_TYPE1_ID = START_SEQ + 4;

    public static final int MEAL1_ID = START_SEQ + 12;

    public static final MealType MEAL_TYPE1 = new MealType(MEAL_TYPE1_ID, "Кофе");
    public static final MealType MEAL_TYPE2 = new MealType(MEAL_TYPE1_ID + 1, "Салат");
    public static final MealType MEAL_TYPE3 = new MealType(MEAL_TYPE1_ID + 2, "Борщ");
    public static final MealType MEAL_TYPE4 = new MealType(MEAL_TYPE1_ID + 3, "Паста");
    public static final MealType MEAL_TYPE5 = new MealType(MEAL_TYPE1_ID + 4, "Чай");
    public static final MealType MEAL_TYPE6 = new MealType(MEAL_TYPE1_ID + 5, "Амлет");
    public static final MealType MEAL_TYPE7 = new MealType(MEAL_TYPE1_ID + 6, "Щи");
    public static final MealType MEAL_TYPE8 = new MealType(MEAL_TYPE1_ID + 7, "Плов");

    public static final int RESTAURANT_ID = START_SEQ + 2;
    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT_ID);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1);

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDate.now(), MEAL_TYPE1, 60, RESTAURANT);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, LocalDate.now(), MEAL_TYPE2, 80, RESTAURANT);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, LocalDate.now(), MEAL_TYPE3, 100, RESTAURANT);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, LocalDate.now(), MEAL_TYPE4, 120, RESTAURANT);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, LocalDate.now(), MEAL_TYPE5, 50, RESTAURANT2);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, LocalDate.now(), MEAL_TYPE6, 70, RESTAURANT2);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, LocalDate.now(), MEAL_TYPE7, 90, RESTAURANT2);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, LocalDate.now(), MEAL_TYPE8, 150, RESTAURANT2);

    public static final List<Meal> MEALS = List.of(MEAL1, MEAL2, MEAL3, MEAL4);
    public static final List<Meal> MEALS2 = List.of(MEAL5, MEAL6, MEAL7, MEAL8);

    public static Meal getCreated() {
        return new Meal(null, LocalDate.now(), MEAL_TYPE5, 50);
    }

    public static MealTo getUpdated() {
        return new MealTo(MEAL1_ID, LocalDate.now(), MEAL_TYPE3.getId(), 150, RESTAURANT2.getId());
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
