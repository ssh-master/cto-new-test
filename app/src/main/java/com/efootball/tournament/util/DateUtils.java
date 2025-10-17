package com.efootball.tournament.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class for date formatting and manipulation operations.
 */
public class DateUtils {
    
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DISPLAY_DATE_FORMAT = "MMM dd, yyyy";
    private static final String DISPLAY_TIME_FORMAT = "HH:mm";
    
    private DateUtils() {
    }
    
    public static String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.getDefault());
        return format.format(date);
    }
    
    public static String formatTime(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DISPLAY_TIME_FORMAT, Locale.getDefault());
        return format.format(date);
    }
    
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault());
        return format.format(date);
    }
}
