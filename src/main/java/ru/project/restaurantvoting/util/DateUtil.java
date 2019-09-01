package ru.project.restaurantvoting.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import ru.project.restaurantvoting.util.exception.ChangeVoteException;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtil {
    public static final LocalTime TIME_TO_REVOTE = LocalTime.of(11, 0);

    public static void checkTime(LocalTime time) {
        if (time.isAfter(TIME_TO_REVOTE)) {
            throw new ChangeVoteException("Unable to vote after " + TIME_TO_REVOTE);
        }
    }

    public static LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }
}
