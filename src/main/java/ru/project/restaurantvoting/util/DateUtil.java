package ru.project.restaurantvoting.util;

import ru.project.restaurantvoting.util.exception.ChangeVoteException;

import java.time.LocalTime;

public class DateUtil {
    public static final LocalTime TIME_TO_REVOTE = LocalTime.of(14, 0);

    public static void checkTime() {
        if (LocalTime.now().isAfter(TIME_TO_REVOTE)) {
            throw new ChangeVoteException("Unable to vote after " + TIME_TO_REVOTE);
        }
    }
}
