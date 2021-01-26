package cn.org.rookie.jeesdp.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author LIHAITAO
 */

public abstract class StringUtils {
    public static final Pattern humpPattern = Pattern.compile("[A-Z]");
    public static final Pattern underlinePattern = Pattern.compile("_(\\w)");

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 字符串集合添加分隔符，返回字符串，带前缀后缀
     *
     * @param delimiter 分割符
     * @param prefix    前缀
     * @param suffix    后缀
     * @param source    要添加分隔符的字符串集合
     * @return 添加分隔符的字符串
     */

    public static String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix, Collection<String> source) {
        StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);
        for (String s : source) {
            joiner.add(s);
        }
        return joiner.toString();
    }

    /**
     * 字符串数组添加分隔符，返回字符串，带前缀后缀
     *
     * @param delimiter 分割符
     * @param prefix    前缀
     * @param suffix    后缀
     * @param source    要添加分隔符的字符串数组
     * @return 添加分隔符的字符串
     */

    public static String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix, String[] source) {
        return join(delimiter, prefix, suffix, Arrays.asList(source));
    }

    /**
     * 字符串集合添加分隔符，返回字符串
     *
     * @param delimiter 分割符
     * @param source    要添加分隔符的字符串集合
     * @return 添加分隔符的字符串
     */

    public static String join(CharSequence delimiter, Collection<String> source) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (String s : source) {
            joiner.add(s);
        }
        return joiner.toString();
    }

    /**
     * 字符串数组添加分隔符，返回字符串
     *
     * @param delimiter 分割符
     * @param source    要添加分隔符的字符串数组
     * @return 添加分隔符的字符串
     */

    public static String join(CharSequence delimiter, String[] source) {
        return join(delimiter, Arrays.asList(source));
    }

    /**
     * 驼峰转下划线
     *
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */

    public static String humpToUnderline(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str 要转换的字符串
     * @return 转换后的字符串
     */

    public static String underlineToHump(String str) {
        Matcher matcher = Pattern.compile("_(\\w)").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
