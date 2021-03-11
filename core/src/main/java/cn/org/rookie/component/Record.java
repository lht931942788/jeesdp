package cn.org.rookie.component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Record extends LinkedHashMap<String, Object> {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

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

    public static Record describe(Object bean) {

        Record record = new Record();
        if (bean instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) bean;
            map.forEach((key, value) -> {
                if (key instanceof String) {
                    if (value instanceof String[]) {
                        String[] values = (String[]) value;
                        if (values.length == 1) {
                            String field = values[0];
                            try {
                                Date date = SIMPLE_DATE_FORMAT.parse(field);
                                date.setTime(date.getTime() + 8 * 60 * 60 * 1000);
                                record.put(key.toString(), date);
                            } catch (ParseException e) {
                                record.put(key.toString(), field);
                            }
                        } else {
                            record.put(key.toString(), value);
                        }
                    } else {
                        record.put(key.toString(), value);
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

    public List<?> getList(String name) {
        return (List<?>) get(name);
    }
}
