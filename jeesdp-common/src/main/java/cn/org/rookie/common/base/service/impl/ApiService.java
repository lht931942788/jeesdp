package cn.org.rookie.common.base.service.impl;

import cn.org.rookie.common.base.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

public class ApiService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int insert(Map<String, Object> params, String tableName) {
        Table table = new Table();
        List<String> fields = new ArrayList<>();
        table.getColumns().forEach((column -> {
            fields.add(column.getName());
            if (column.getDefult() != null) {
                params.put(column.getName(), column.getDefult());
            }
        }));
        return jdbcTemplate.update("insert into (" + String.join(",", fields) + ") values (:" + String.join(",:", fields) + ")", params);
    }

    public int delete(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return jdbcTemplate.update("delete from " + tableName + " where ", params);
    }

    public int update(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return 0;
    }

    public int select(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return 0;
    }

    public int selectById(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return 0;
    }

    public int page(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return 0;
    }

    public int count(Map<String, Object> params, String tableName) {
        params.values().toArray();
        return 0;
    }
}
