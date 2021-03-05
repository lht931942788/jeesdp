package cn.org.rookie.mapper;

import cn.org.rookie.mapper.sql.where.Wrapper;
import cn.org.rookie.mapper.utils.SqlBuilderContext;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.util.List;

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

}

class BaseMapperProvider {

    public String insert(ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).insert().build();
    }

    public String deleteByPrimary(Object o, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).select().byPrimary().build();
    }

    public String delete(Wrapper wrapper, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).delete().where(wrapper).build();
    }

    public String updateByPrimary(Object o, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).update().byPrimary().build();
    }

    public String update(Object entity, Wrapper wrapper, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).update().where(wrapper).build();
    }

    public String select(Wrapper wrapper, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).select().where(wrapper).build();
    }

    public String selectByPrimary(Object o, ProviderContext context) {
        return SqlBuilderContext.getSqlBuilder(context).select().byPrimary().build();
    }

}
