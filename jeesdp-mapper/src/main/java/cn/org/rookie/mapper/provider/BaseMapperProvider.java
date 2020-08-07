package cn.org.rookie.mapper.provider;

import cn.org.rookie.mapper.sql.where.Wrapper;
import cn.org.rookie.mapper.utils.SqlBuilderContext;
import org.apache.ibatis.builder.annotation.ProviderContext;

@SuppressWarnings("unused")
public class BaseMapperProvider {

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
