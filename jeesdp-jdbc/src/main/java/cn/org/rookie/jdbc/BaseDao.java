package cn.org.rookie.jdbc;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
public class BaseDao<T, ID> {

    protected final NamedParameterJdbcTemplate jdbcTemplate;
    protected BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<>();

    public BaseDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insert(T entity) {
        Map<String, Object> params = Objects.requireNonNull(describe(entity));
        String sql = "";
        return jdbcTemplate.update(sql, params);
    }

    public long delete(T entity) {
        Map<String, Object> params = Objects.requireNonNull(describe(entity));
        String sql = "";
        return jdbcTemplate.update(sql, params);
    }

    public long deleteById(ID id) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("id", id);
        String sql = "";
        return jdbcTemplate.update(sql, params);
    }

    public List<T> find() {
        String sql = "";
        return jdbcTemplate.query(sql, mapper);
    }

    public List<T> find(T entity) {
        String sql = "";
        return jdbcTemplate.query(sql, mapper);
    }

    private Map<String, Object> describe(Object bean) {
        try {
            bean.getClass().getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
