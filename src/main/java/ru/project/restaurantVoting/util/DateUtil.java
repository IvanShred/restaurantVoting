package ru.project.restaurantVoting.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public static int getCurrentTime(){
        return LocalDateTime.now().getHour();
    }

    public static void checkTime() {
        if (DateUtil.getCurrentTime() >= 23) {
            throw new RuntimeException();
        }
    }
}
