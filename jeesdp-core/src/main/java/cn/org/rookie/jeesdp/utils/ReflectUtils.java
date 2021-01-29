package cn.org.rookie.jeesdp.utils;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 反射工具类
 *
 * @author LIHAITAO
 */

public abstract class ReflectUtils extends BeanUtils {

    /**
     * 将Map转为实体类
     *
     * @param source 要转换的Map
     * @param type   要转成的实体类
     * @param <T>    实体类泛型
     * @return 转换后的对象
     */

    public static <T> T populate(Map<String, Object> source, Class<T> type) {

        try {
            T instance = type.getConstructor().newInstance();
            getAllField(type).forEach(field -> {
                field.setAccessible(true);
                try {
                    field.set(instance, source.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将对象转为Map
     *
     * @param bean 要转换的对象
     * @return 转换后的Map
     */

    public static Map<String, Object> describe(Object bean) {

        Map<String, Object> map = new LinkedHashMap<>();

        getAllField(bean.getClass()).forEach(field -> {
            try {
                map.put(field.getName(), field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * 获取实体类的所有字段，包括父类
     *
     * @param type 实体类的类型
     * @return 所有字段的集合
     */

    public static List<Field> getAllField(Class<?> type) {

        List<Field> fields = new ArrayList<>();

        Class<?> superclass = type.getSuperclass();
        if (!superclass.equals(Object.class)) {
            fields.addAll(getAllField(superclass));
        }
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        return fields;
    }
}
