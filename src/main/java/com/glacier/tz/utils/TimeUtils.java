package com.glacier.tz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public class TimeUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();
    static {
        dateFormat.setLenient(false);
    }

    public static String daySkip(int skip) {
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, skip);
        return dateFormat.format(calendar.getTime());
    }

    public static String weekSkip(int skip) {
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, skip);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dateFormat.format(calendar.getTime());
    }


    public static String monthSkip(int skip) {
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, skip);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return dateFormat.format(calendar.getTime());
    }

    public static String yearSkip(int skip) {
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, skip);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return dateFormat.format(calendar.getTime());
    }

    public static boolean checkDateFormat(String date) {
        try {
            if ( date != null && date.equals(dateFormat.format(dateFormat.parse(date))) )
                return true;
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean aGreaterThanb(String a, String b) {
        try {
            if (dateFormat.parse(a).getTime() > dateFormat.parse(b).getTime()) {
                return true;
            }
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
    }

}
