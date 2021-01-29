package cn.org.rookie.jeesdp.common.service;

import cn.org.rookie.jeesdp.common.entity.BaseEntity;
import cn.org.rookie.mapper.sql.where.Wrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

public interface BaseService<T extends BaseEntity, E> extends ApplicationContextAware, InitializingBean {

    boolean insert(T entity);

    boolean delete(E id);

    boolean delete(E[] ids);

    boolean update(T entity);

    T selectById(E id);

    T selectOne(Wrapper wrapper);

    T selectOne(T entity);

    List<T> select();

    List<T> select(E[] ids);

    List<T> select(T entity);

    List<T> select(Wrapper wrapper);

    List<T> select(T entity, Wrapper wrapper);
}
