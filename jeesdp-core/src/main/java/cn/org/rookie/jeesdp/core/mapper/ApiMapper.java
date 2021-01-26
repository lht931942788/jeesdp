package cn.org.rookie.jeesdp.core.mapper;

import cn.org.rookie.jeesdp.component.Entity;
import cn.org.rookie.jeesdp.component.EntityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApiMapper {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int insert(String name, Map<String, Object> param) {
        return jdbcTemplate.update(EntityContext.get(name).insert().toString(), param);
    }

    public int delete(String name, Map<String, Object> param) {
        String sql = EntityContext.get(name).delete().toString();
        return jdbcTemplate.update(sql, param);
    }

    public int delete(String name, Object id) {
        Map<String, Object> param = new HashMap<>();
        Entity entity = EntityContext.get(name);
        param.put(entity.getTableInfo().getPrimary().getName(), id);
        return jdbcTemplate.update(entity.delete().byPrimary().toString(), param);
    }

    public int update(String name, Map<String, Object> param) {
        return jdbcTemplate.update(EntityContext.get(name).update().byPrimary().toString(), param);
    }

    public Object select(String name, Object id) {
        Map<String, Object> param = new HashMap<>();
        Entity entity = EntityContext.get(name);
        param.put(entity.getTableInfo().getPrimary().getName(), id);
        return jdbcTemplate.query(entity.select().byPrimary().toString(), param, new BeanPropertyRowMapper<>(entity.getEntity()));
    }

    public List<?> select(String name, Map<String, Object> param) {
        Entity entity = EntityContext.get(name);
        return jdbcTemplate.query(entity.select().toString(), param, new BeanPropertyRowMapper<>(entity.getEntity()));
    }

}
