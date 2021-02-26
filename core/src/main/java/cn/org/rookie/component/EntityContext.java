package cn.org.rookie.component;

import java.util.concurrent.ConcurrentHashMap;

public class EntityContext {

    private static final ConcurrentHashMap<String, Entity> ENTITY_CONTEXT = new ConcurrentHashMap<>();

    public static void put(String name, Entity entity) {
        ENTITY_CONTEXT.put(name, entity);
    }

    public static Entity get(String name) {
        return ENTITY_CONTEXT.get(name);
    }
}
