package cn.org.rookie.context;

import java.util.*;

/**
 * @author LIHAITAO
 */

public class DictContext {

    private static final LinkedHashMap<String, LinkedHashMap<String, Object>> DICTS = new LinkedHashMap<>();

    public static void init(List<Map<String, Object>> dicts) {
        Iterator<Map<String, Object>> iterator = dicts.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> row = iterator.next();
            LinkedHashMap<String, Object> dict;
            Object parent = row.get("parent");
            String code = row.get("code").toString();
            if ("0".equals(parent)) {
                dict = new LinkedHashMap<>();
                DICTS.put(code, dict);
            } else {
                dict = DICTS.get(parent.toString());
                dict.put(code, dict.get("name"));
            }
            iterator.remove();
        }
    }

    public static Map<String, Object> putDict(String dictName, LinkedHashMap<String, Object> dict) {
        return DICTS.put(dictName, dict);
    }

    public static Map<String, Object> getDict(String dictName) {
        return DICTS.get(dictName);
    }

    public static String getValue(String dictName, String dictKey) {
        return DICTS.get(dictName).get(dictKey).toString();
    }

    public static String getKey(String dictName, String dictValue) {
        Set<Map.Entry<String, Object>> entries = DICTS.get(dictName).entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getValue().equals(dictValue)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
