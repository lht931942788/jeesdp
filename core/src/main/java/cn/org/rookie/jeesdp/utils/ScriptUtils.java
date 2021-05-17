package cn.org.rookie.jeesdp.utils;

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
        Binding binding = new Binding();
        binding.setProperty("demo", 2);
        System.out.println(exec("import java.util.Random;\n" +
                        "Random random = new Random();\n",
                binding));
        System.out.println(binding.getVariables());
    }

}
