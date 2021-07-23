package cn.org.rookie.jeesdp.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
@MappedTypes(Map.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonTypeHandler implements TypeHandler<Map<String, Object>> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Map<String, Object> map, JdbcType jdbcType) throws SQLException {
        try {
            String json = mapper.writeValueAsString(map);
            preparedStatement.setString(i, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> getResult(ResultSet resultSet, String s) throws SQLException {
        try {
            return mapper.readValue(resultSet.getString(s), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getResult(ResultSet resultSet, int i) throws SQLException {
        try {
            return mapper.readValue(resultSet.getString(i), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return mapper.readValue(callableStatement.getString(i), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
