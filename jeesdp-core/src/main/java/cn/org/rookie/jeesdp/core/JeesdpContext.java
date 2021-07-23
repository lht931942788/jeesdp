package cn.org.rookie.jeesdp.core;

import java.util.*;

/**
 * @author LIHAITAO
 */

public class JeesdpContext {

    private static final LinkedHashMap<String, Map<String, Object>> DICTIONARIES = new LinkedHashMap<>();

    public static void putDict(String dictName, Map<String, Object> dict) {
        DICTIONARIES.put(dictName, dict);
    }

    public static Map<String, Object> getDict(String dictName) {
        return DICTIONARIES.get(dictName);
    }

    public static String getValue(String dictName, String dictKey) {
        return DICTIONARIES.get(dictName).get(dictKey).toString();
    }

    public static String getKey(String dictName, String dictValue) {
        Set<Map.Entry<String, Object>> entries = DICTIONARIES.get(dictName).entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getValue().equals(dictValue)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
