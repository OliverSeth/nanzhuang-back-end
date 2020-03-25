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

    public static String lastYear(String period) {
        return (Integer.parseInt(period.substring(0, 4)) - 1) + period.substring(4);
    }

    public static String lastMonth(String period) {
        if (period.substring(4, 6).equals("01")) {
            return lastYear(period).substring(0, 4) + "12" + period.substring(6);
        } else {
            return period.substring(0, 4) + (Integer.parseInt(period.substring(4, 6)) - 1) + period.substring(6);
        }
    }

    public static String lastDays(String period) {
        if (period.substring(6).equals("01")) {
            return lastMonth(period).substring(0, 6) + "03";
        } else {
            return period.substring(0, 6) + (Integer.parseInt(period.substring(6, 8)) - 1);
        }
    }
}
