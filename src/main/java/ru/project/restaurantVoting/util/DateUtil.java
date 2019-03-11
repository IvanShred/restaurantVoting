package ru.project.restaurantVoting.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public static int getCurrentTime(){
        return LocalDateTime.now().getHour();
    }
}
