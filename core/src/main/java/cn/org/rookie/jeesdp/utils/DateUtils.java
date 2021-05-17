package cn.org.rookie.jeesdp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LIHAITAO
 */

public abstract class DateUtils {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return SDF.format(new Date());
    }

    public static String now(String patten) {
        SDF.applyPattern(patten);
        return SDF.format(new Date());
    }

    public static String format(Date date) {
        return SDF.format(new Date());
    }

    public static String format(long date) {
        return SDF.format(new Date());
    }

    public static String format(Date date, String patten) {
        SDF.applyPattern(patten);
        return SDF.format(date);
    }

    public static String format(long date, String patten) {
        SDF.applyPattern(patten);
        return SDF.format(date);
    }

    public static Date parse(String date) {
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String date, String patten) {
        try {
            SDF.applyPattern(patten);
            return SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
