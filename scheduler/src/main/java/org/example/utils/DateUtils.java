package org.example.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateUtils {

    public static final ZoneId istZone = ZoneId.of("Asia/Kolkata");

    public static LocalTime getTimeFromDateTime(LocalDateTime datetime) {
        return datetime.toLocalTime();
    }

    public static LocalDate getDateFromDateTime(LocalDateTime datetime) {
        return datetime.toLocalDate();
    }
}
