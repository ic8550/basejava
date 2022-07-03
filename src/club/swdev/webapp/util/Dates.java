package club.swdev.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class Dates {
    public static final LocalDate NOW = LocalDate.of(3000, 12, 31);

    public static LocalDate of(int year, Month month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
