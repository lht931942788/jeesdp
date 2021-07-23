package cn.org.rookie.jeesdp.mapper;

import cn.org.rookie.jeesdp.mapper.annotation.Column;
import cn.org.rookie.jeesdp.mapper.annotation.Table;
import cn.org.rookie.jeesdp.mapper.annotation.Transient;
import cn.org.rookie.jeesdp.mapper.sql.Wrapper;
import cn.org.rookie.jeesdp.mapper.utils.SqlBuilderContext;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BaseMapper<T, E> {

    @InsertProvider(type = BaseMapperProvider.class, method = "insert")
    int insert(T entity);

    @DeleteProvider(type = BaseMapperProvider.class, method = "deleteByPrimary")
    int deleteByPrimary(E id);

    @DeleteProvider(type = BaseMapperProvider.class, method = "delete")
    int delete(@Param("wrapper") Wrapper wrapper);

    @UpdateProvider(type = BaseMapperProvider.class, method = "updateByPrimary")
    int updateByPrimary(T entity);

    @DeleteProvider(type = BaseMapperProvider.class, method = "update")
    int update(@Param("entity") T entity, @Param("wrapper") Wrapper wrapper);

    @SelectProvider(type = BaseMapperProvider.class, method = "selectByPrimary")
    T selectByPrimary(@Param("id") E id);

    @SelectProvider(type = BaseMapperProvider.class, method = "select")
    T selectOne(@Param("wrapper") Wrapper wrapper);

    @SelectProvider(type = BaseMapperProvider.class, method = "select")
    List<T> select();

    @SelectProvider(type = BaseMapperProvider.class, method = "select")
    List<T> selectList(@Param("wrapper") Wrapper wrapper);

    class BaseMapperProvider {
        private static final ConcurrentHashMap<String, Map<String, Object>> CONTEXT = new ConcurrentHashMap<>();

        private static Map<String, Object> getTable(ProviderContext context) {
            Class<?> entityType = (Class<?>) ((ParameterizedType) (context.getMapperType().getGenericInterfaces()[0])).getActualTypeArguments()[0];
            Map<String, Object> result = CONTEXT.get(entityType.getName());
            if (result == null) {
                result = new HashMap<>();
                result.put("table", entityType.getAnnotation(Table.class).value());
                Field[] fields = entityType.getDeclaredFields();
                List<Column> columns = new ArrayList<>();
                for (Field field : fields) {
                    if (field.getAnnotation(Transient.class) == null) {
                        Column column = field.getAnnotation(Column.class);
                        if (column != null) {
                            columns.add(column);
                            if (column.primary()) {
                                result.put("primary", column);
                            }
                        }
                    }
                }
                result.put("columns", columns);
                CONTEXT.put(entityType.getName(), result);
            }
            return result;
        }


        public String insert(ProviderContext context) {

            return null;
        }

        public String deleteByPrimary(Object o, ProviderContext context) {
            return null;
        }

        public String delete(Wrapper wrapper, ProviderContext context) {
            return null;
        }

        public String updateByPrimary(Object o, ProviderContext context) {
            return null;
        }

        public String update(Object entity, Wrapper wrapper, ProviderContext context) {
            return null;
        }

        public String select(Wrapper wrapper, ProviderContext context) {
            return null;
        }

        public String selectByPrimary(Object o, ProviderContext context) {
            return null;
        }
    }

}

