//package ru.project.restaurantvoting;
//
//import ru.project.restaurantvoting.model.Meal;
//import ru.project.restaurantvoting.model.Restaurant;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;
//
//public class MealTestData {
//    public static final int MEAL1_ID = START_SEQ + 4;
//
//    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDate.now(), "Кофе", 60);
//    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, LocalDate.now(), "Салат", 80);
//    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, LocalDate.now(), "Борщ", 100);
//    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, LocalDate.now(), "Паста", 120);
//    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, LocalDate.now(), "Чай", 50);
//    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, LocalDate.now(), "Амлет", 70);
//    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, LocalDate.now(), "Щи", 90);
//    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, LocalDate.now(), "Плов", 150);
//    public static final int RESTAURANT_ID = START_SEQ + 2;
//    public static final Restaurant RESTAURANT = new Restaurant(START_SEQ + 2);
//
//    public static final List<Meal> MEALS = List.of(MEAL1, MEAL2, MEAL3, MEAL4);
//
//    public static Meal getCreated() {
//        return new Meal(null, LocalDate.now(), "Созданное блюдо", 50/*, RESTAURANT*/);
//    }
//
//    public static Meal getUpdated() {
//        return new Meal(MEAL1_ID, LocalDate.now(), "Обновленное блюдо", 150, RESTAURANT);
//    }
//
//    public static void assertMatch(Meal actual, Meal expected) {
//        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
//    }
//
//    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
//        assertMatch(actual, List.of(expected));
//    }
//
//    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
//        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
//    }
//}
