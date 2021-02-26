package cn.org.rookie.utils;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;

/**
 * Groovy 脚本工具类
 *
 * @author LIHAITAO
 */

public abstract class ScriptUtils {

    private static final GroovyClassLoader CLASS_LOADER = new GroovyClassLoader();

    /**
     * @param script 脚本文件
     * @return 脚本的Class
     */

    public static Class<?> parse(File script) {
        try {
            return (Class<?>) CLASS_LOADER.parseClass(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}