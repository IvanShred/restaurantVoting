package ru.project.restaurantvoting;

import ru.project.restaurantvoting.model.Restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT_ID, "Арарат", "ул. Ноябрьская, 58/1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "Забой", "ул. Институтская, 2");

    public static Restaurant getCreated() {
        return new Restaurant(null, "new address", "new name");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
