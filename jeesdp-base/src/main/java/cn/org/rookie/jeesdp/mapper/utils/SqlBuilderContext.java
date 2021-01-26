package cn.org.rookie.jeesdp.mapper.utils;

import cn.org.rookie.jeesdp.mapper.entity.PrimaryInfo;
import cn.org.rookie.jeesdp.mapper.entity.TableInfo;
import cn.org.rookie.jeesdp.mapper.sql.SQLBuilder;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SqlBuilderContext {

    private static final Map<String, SQLBuilder> CONTEXT = new ConcurrentHashMap<>();

    public static SQLBuilder getSqlBuilder(ProviderContext context) {
        String name = getEntityType(context).getName();
        SQLBuilder sqlBuilder = CONTEXT.get(name);
        if (sqlBuilder == null) {
            sqlBuilder = new SQLBuilder(getEntityType(context));
            CONTEXT.put(name, sqlBuilder);
        }
        return sqlBuilder;
    }

    public static SQLBuilder getSqlBuilder(String name) {
        return CONTEXT.get(name);
    }

    public static TableInfo getTableInfo(String name) {
        return CONTEXT.get(name).getTableInfo();
    }

    public static PrimaryInfo getPrimaryInfo(String name) {
        return CONTEXT.get(name).getPrimaryInfo();
    }

    private static Class<?> getEntityType(ProviderContext context) {
        return (Class<?>) ((ParameterizedType) (context.getMapperType().getGenericInterfaces()[0])).getActualTypeArguments()[0];
    }
}
