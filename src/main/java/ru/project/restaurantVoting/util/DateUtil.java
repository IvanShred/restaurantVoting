package ru.project.restaurantVoting.util;

import ru.project.restaurantVoting.util.exception.ChangeVoteException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final int TIME_TO_REVOTE = 23;

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public static int getCurrentTime(){
        return LocalDateTime.now().getHour();
    }

    public static void checkTime() {
        if (DateUtil.getCurrentTime() >= TIME_TO_REVOTE) {
            throw new ChangeVoteException("Unable to re-vote after " + TIME_TO_REVOTE);
        }
    }
}
