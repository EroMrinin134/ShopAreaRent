package com.company.shoparearent.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// static class
public final class DateConverter {

    private DateConverter() {}

    public static LocalDate convertToLocalDate(Date date) {
        if (date == null)
            return null;

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
