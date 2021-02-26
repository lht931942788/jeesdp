package cn.org.rookie.component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Record extends LinkedHashMap<String, Object> {

    public static Record describe(Object bean) {
        Record record = new Record();

        getAllField(bean.getClass()).forEach(field -> {
            try {
                record.put(field.getName(), field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return record;
    }

    private static List<Field> getAllField(Class<?> type) {
        List<Field> fields;

        Class<?> superclass = type.getSuperclass();
        if (!superclass.equals(Object.class)) {
            fields = new ArrayList<>(getAllField(superclass));
        } else {
            fields = new ArrayList<>(Arrays.asList(type.getDeclaredFields()));
        }
        return fields;
    }

    public String getString(String name) {
        return (String) get(name);
    }

    public Boolean getBoolean(String name) {
        return (Boolean) get(name);
    }

    public Short getShort(String name) {
        return (Short) get(name);
    }

    public char getChar(String name) {
        return (char) get(name);
    }

    public Integer getInteger(String name) {
        return (Integer) get(name);
    }

    public Long getLong(String name) {
        return (Long) get(name);
    }

    public Double getDouble(String name) {
        return (Double) get(name);
    }

    public Float getFloat(String name) {
        return (Float) get(name);
    }

    public Date getDate(String name) {
        return (Date) get(name);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name, Class<T> type) {
        return (T) get(name);
    }

    public <T> T populate(Class<T> type) {

        try {
            T instance = type.getConstructor().newInstance();
            getAllField(type).forEach(field -> {
                field.setAccessible(true);
                try {
                    field.set(instance, get(field.getName()));
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

}
