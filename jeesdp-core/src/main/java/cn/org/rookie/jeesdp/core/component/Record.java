package cn.org.rookie.jeesdp.core.component;


import cn.org.rookie.jeesdp.core.utils.JsonUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Record extends LinkedHashMap<String, Object> implements Serializable {

    public static Record describe(Object bean) {

        Record record = new Record();
        if (bean instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) bean;
            map.forEach((key, value) -> {
                if (key instanceof String) {
                    String keyString = key.toString();
                    if (value instanceof String[]) {
                        String[] values = (String[]) value;
                        if (values.length == 1) {
                            record.put(keyString, values[0]);
                        } else {
                            record.put(keyString, value);
                        }
                    } else {
                        record.put(keyString, value);
                    }
                } else {
                    throw new RuntimeException("键必须为字符串类型");
                }
            });
        } else {
            getAllField(bean.getClass()).forEach(field -> {
                try {
                    record.put(field.getName(), field.get(bean));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return record;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name, Class<T> type) {
        return (T) get(name);
    }

    public String getString(String name) {
        return get(name, String.class);
    }

    public Boolean getBoolean(String name) {
        return get(name, Boolean.class);
    }

    public Short getShort(String name) {
        return get(name, Short.class);
    }

    public char getChar(String name) {
        return get(name, char.class);
    }

    public Integer getInteger(String name) {
        return get(name, Integer.class);
    }

    public Long getLong(String name) {
        return get(name, Long.class);
    }

    public Double getDouble(String name) {
        return get(name, Double.class);
    }

    public Float getFloat(String name) {
        return get(name, Float.class);
    }

    public Date getDate(String name) {
        return get(name, Date.class);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String name, Class<T> type) {
        return (List<T>) get(name);
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

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }

    public String toIndentedJsonString() {
        return JsonUtils.toIndentedJsonString(this);
    }
}
