package ru.project.restaurantVoting;

import ru.project.restaurantVoting.model.Vote;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.project.restaurantVoting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final int RESTAURANT_ID = START_SEQ + 3;

    public static final int USER_ID = START_SEQ;

    public static final int ADMIN_ID = START_SEQ + 1;

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

}
