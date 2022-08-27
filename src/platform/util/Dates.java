package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static LocalDateTime toLocaleDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }
}