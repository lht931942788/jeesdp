package cn.org.rookie.jeesdp.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author LIHAITAO
 */
public abstract class JarUtils {
    private static final String JAR_URL_TEMPLATE = "jar:file:/%s!/";
    private static final String JAR_SUFFIX = "jar";
    private static final String CLASS_SUFFIX = "class";

    public static void load(String path) {
        String uri = String.format(JAR_URL_TEMPLATE, path);
        try {
            URL url = new URL(uri);
            JarFile jarFile = new JarFile(path);
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            URLClassLoader bootClassLoader = new URLClassLoader(new URL[]{new URL(String.format(JAR_URL_TEMPLATE, path) + "BOOT-INF/classes/")});
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                if (!jarEntry.isDirectory()) {
                    String name = jarEntry.getName();
                    System.out.println(name);
                    int index = name.lastIndexOf(".");
                    if (CLASS_SUFFIX.equals(name.substring(index + 1))) {
                        String className = name.substring(0, index);
                        Class<?> loadedClass;
                        if (name.contains("BOOT-INF/classes/")) {
                            loadedClass = bootClassLoader.loadClass(className.replace("BOOT-INF/classes/", "").replace("/", "."));
                        } else {
                            loadedClass = classLoader.loadClass(className.replace("/", "."));
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        load("C:/Users/admin/IdeaProjects/jeesdp/core/target/core-1.26.jar");
    }
}
