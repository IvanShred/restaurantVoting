package ru.project.restaurantVoting;

import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.util.DateUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.project.restaurantVoting.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 4;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, DateUtil.getCurrentDate(), "Кофе", 60);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, DateUtil.getCurrentDate(), "Салат", 80);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, DateUtil.getCurrentDate(), "Борщ", 100);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, DateUtil.getCurrentDate(), "Паста", 120);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, DateUtil.getCurrentDate(), "Чай", 50);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, DateUtil.getCurrentDate(), "Амлет", 70);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, DateUtil.getCurrentDate(), "Щи", 90);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, DateUtil.getCurrentDate(), "Плов", 150);
    public static final Restaurant RESTAURANT = new Restaurant(START_SEQ + 2);

    public static final List<Meal> MEALS = List.of(MEAL1, MEAL2, MEAL3, MEAL4);

    public static Meal getCreated() {
        return new Meal(null, DateUtil.getCurrentDate(), "Созданное блюдо", 50, RESTAURANT);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, DateUtil.getCurrentDate(), "Обновленное блюдо", 150, RESTAURANT);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
