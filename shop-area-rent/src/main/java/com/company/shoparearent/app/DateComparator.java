package com.company.shoparearent.app;

import java.time.LocalDate;

// static class
public final class DateComparator {

    private DateComparator() {}

    public static boolean isBetween(LocalDate date, LocalDate dateStart, LocalDate dateEnd) {
        return !date.isBefore(dateStart) && !date.isAfter(dateEnd);
    }

    public static boolean isBetween(LocalDate dateStart1, LocalDate dateEnd1, LocalDate dateStart2, LocalDate dateEnd2) {
        return isBetween(dateStart1, dateStart2, dateEnd2) ||
                isBetween(dateEnd1, dateStart2, dateEnd2) ||
                isBetween(dateStart2, dateStart1, dateEnd1) ||
                isBetween(dateEnd2, dateStart1, dateEnd1);
    }
}
