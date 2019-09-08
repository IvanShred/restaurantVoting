package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.to.response.VoteResponseTo;

import java.time.LocalDate;

public class VoteUtil {

    public static VoteResponseTo convertToResponse(int voteId, LocalDate date, int userId, int restaurantId) {
        return new VoteResponseTo(voteId, date, userId, restaurantId);
    }
}
