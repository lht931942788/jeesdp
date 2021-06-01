package cn.org.rookie.jeesdp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LIHAITAO
 */

public abstract class DateUtils {
    public static final SimpleDateFormat HH_MM_SS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return YYYY_MM_DD_HH_MM_SS.format(new Date());
    }

    public static String now(String patten) {
        YYYY_MM_DD_HH_MM_SS.applyPattern(patten);
        return YYYY_MM_DD_HH_MM_SS.format(new Date());
    }

    public static String format(Date date) {
        return YYYY_MM_DD_HH_MM_SS.format(new Date());
    }

    public static String format(long date) {
        return YYYY_MM_DD_HH_MM_SS.format(new Date());
    }

    public static String format(Date date, String patten) {
        YYYY_MM_DD_HH_MM_SS.applyPattern(patten);
        return YYYY_MM_DD_HH_MM_SS.format(date);
    }

    public static String format(long date, String patten) {
        YYYY_MM_DD_HH_MM_SS.applyPattern(patten);
        return YYYY_MM_DD_HH_MM_SS.format(date);
    }

    public static Date parse(String date) {
        try {
            return YYYY_MM_DD_HH_MM_SS.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String date, String patten) {
        try {
            YYYY_MM_DD_HH_MM_SS.applyPattern(patten);
            return YYYY_MM_DD_HH_MM_SS.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
