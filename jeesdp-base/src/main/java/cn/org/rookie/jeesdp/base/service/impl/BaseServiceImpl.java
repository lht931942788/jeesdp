package cn.org.rookie.jeesdp.base.service.impl;

import cn.org.rookie.jeesdp.base.entity.BaseEntity;
import cn.org.rookie.jeesdp.base.service.BaseService;
import cn.org.rookie.jeesdp.mapper.BaseMapper;
import cn.org.rookie.jeesdp.mapper.entity.PrimaryInfo;
import cn.org.rookie.jeesdp.mapper.sql.where.Wrapper;
import cn.org.rookie.jeesdp.mapper.utils.SqlBuilderContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public abstract class BaseServiceImpl<T extends BaseEntity, E, M extends BaseMapper<T, E>> implements BaseService<T, E> {

    protected M mapper;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            mapper = applicationContext.getBean((Class<M>) ((ParameterizedType) type).getActualTypeArguments()[2]);
        }
    }

    @Override
    public boolean insert(T entity) {
        Class<? extends BaseEntity> entityClass = entity.getClass();
        PrimaryInfo primaryInfo = SqlBuilderContext.getPrimaryInfo(entityClass.getName());
        Field field;
        try {
            field = entityClass.getDeclaredField(primaryInfo.getFieldName());
            field.setAccessible(true);
            field.set(entity, UUID.randomUUID().toString().replaceAll("-", ""));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return mapper.insert(entity) > 0;
    }

    @Override
    public boolean delete(E id) {
        return mapper.deleteByPrimary(id) > 0;
    }

    @Override
    public boolean delete(E[] ids) {
        return mapper.delete(Wrapper.build().in("id", ids)) > 0;
    }

    @Override
    public boolean update(T entity) {
        return mapper.updateByPrimary(entity) > 0;
    }

    @Override
    public T selectById(E id) {
        return mapper.selectByPrimary(id);
    }

    @Override
    public T selectOne(Wrapper wrapper) {
        return mapper.selectOne(wrapper);
    }

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(Wrapper.build(entity));
    }

    @Override
    public List<T> select() {
        return mapper.select();
    }

    @Override
    public List<T> select(E[] ids) {
        return mapper.selectList(Wrapper.build().in("id", ids));
    }

    @Override
    public List<T> select(T entity) {
        return mapper.selectList(Wrapper.build(entity));
    }

    @Override
    public List<T> select(Wrapper wrapper) {
        return mapper.selectList(wrapper);
    }

    @Override
    public List<T> select(T entity, Wrapper wrapper) {
        return mapper.selectList(Wrapper.build(entity, wrapper));
    }

}
