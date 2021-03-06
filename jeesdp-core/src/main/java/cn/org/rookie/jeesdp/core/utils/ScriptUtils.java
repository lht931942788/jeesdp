package cn.org.rookie.jeesdp.core.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

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

    public static Object exec(String script, Binding binding) {
        GroovyShell shell = new GroovyShell(binding);
        return shell.evaluate(script);
    }

    public static void main(String[] args) throws IOException {
        GroovyShell shell = new GroovyShell();
        System.out.println(shell.evaluate(new File("C:\\Users\\admin\\IdeaProjects\\jeesdp\\core\\src\\main\\resources\\demo.groovy")));
    }

}
