package com.example.demo.utils;

import java.util.Calendar;

/**
 * Created by Oliver Seth on 2020/3/24 21:53
 */
public class PeriodUtils {
    public static boolean validYear(String periodYear) {
        if (periodYear.length() == 4) {
            Integer year = Integer.parseInt(periodYear);
            Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
            return year >= 1971 && year <= currentYear;
        }
        return false;
    }

    public static boolean validMonth(String periodMonth) {
        if (periodMonth.length() == 2) {
            int month = Integer.parseInt(periodMonth);
            return month >= 1 && month <= 12;
        }
        return false;
    }

    public static boolean validDay(String periodDays) {
        if (periodDays.length() == 2) {
            int day = Integer.parseInt(periodDays);
            return day >= 1 && day <= 3;
        }
        return false;
    }
}
