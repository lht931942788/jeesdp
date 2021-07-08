package cn.org.rookie.jeesdp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LIHAITAO
 */

public abstract class DateUtils {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String now(String patten) {
        SIMPLE_DATE_FORMAT.applyPattern(patten);
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String format(Date date) {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String format(long date) {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String format(Date date, String patten) {
        SIMPLE_DATE_FORMAT.applyPattern(patten);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String format(long date, String patten) {
        SIMPLE_DATE_FORMAT.applyPattern(patten);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date parse(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String date, String patten) {
        try {
            SIMPLE_DATE_FORMAT.applyPattern(patten);
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
